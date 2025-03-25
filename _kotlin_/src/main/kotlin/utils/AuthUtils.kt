package utils

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.Payload
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jwt.JWTClaimsSet
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.time.Instant
import java.util.*

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        when (args[0]) {
            "-nk" -> {
                if (args.size == 2 && args[1].length <= 12) {
                    val keyPair = getNewKeyPair()
                    println(
                        "keyPair.private.encoded(base64):\n${
                            Base64.getEncoder().encodeToString(keyPair.private.encoded)
                        }\n" +
                            "keyPair.public.encoded(base64):\n${
                                Base64.getEncoder().encodeToString(keyPair.public.encoded)
                            }\n"
                    )
                    val newToken = getNewToken(keyPair, args[1])
                    println("New token by the keyPair:\n$newToken")
                } else printIfIncorrectArgs()
            }

            "-rt" -> {
                if (args.size == 3 && args[1].length <= 12) {
                    val refreshedToken = refreshToken(args[1], args[2])
                    println("Refreshed token by key and inn:\n$refreshedToken")
                } else printIfIncorrectArgs()
            }

            "-ct" -> {
                if (args.size == 2) {
                    println("This token expires in: ${getTimeBeforeExpiration(args[1])}s")
                } else printIfIncorrectArgs()
            }

            "-help" -> {
                println("Use one of these ways:")
                println("-nk <ofd_inn> - generate new token by ofd inn (new key pair will be generated)")
                println("-rt <ofd_inn> <private_key> - refresh token by ofd inn and existing private key")
                println("-ct <token> - check token")
            }

            else -> printIfIncorrectArgs()
        }
    }
}

fun printIfIncorrectArgs() {
    println("Incorrect arguments. Use -help")
}

fun refreshToken(ofdInn: String, privateKey: String): String {
    val header = JWSHeader.Builder(JWSHeader(JWSAlgorithm.RS512)).build()

    val claims = JWTClaimsSet.Builder()
        .claim("ofdInn", ofdInn)
        .claim("date", Instant.now().epochSecond)
        .build()

    val payload = Payload(claims.toJSONObject())

    val jwsObject = JWSObject(header, payload)
    jwsObject.sign(RSASSASigner(getPrivateKey(privateKey)))

    return jwsObject.serialize()
}

fun getPrivateKey(key: String): PrivateKey {
    val keySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(key))
    val kf: KeyFactory = KeyFactory.getInstance("RSA")
    val privKey: PrivateKey = kf.generatePrivate(keySpec)
    return privKey
}

fun getTimeBeforeExpiration(token: String): Int {
    val payload = JWSObject.parse(token).payload.toJSONObject()

    val tokenDate = payload["date"] as Long
    return ((tokenDate + 3600) - Instant.now().epochSecond).toInt()
}

fun getNewToken(kp: KeyPair, ofdInn: String): String {
    val header = JWSHeader.Builder(JWSHeader(JWSAlgorithm.RS512)).build()

    val claims = JWTClaimsSet.Builder()
        .claim("ofdInn", ofdInn)
        .claim("date", Instant.now().epochSecond)
        .build()

    val payload = Payload(claims.toJSONObject())

    val privateKey = kp.private

    val jwsObject = JWSObject(header, payload)
    jwsObject.sign(RSASSASigner(privateKey))

    val fact = KeyFactory.getInstance("RSA")
    val spec = fact.getKeySpec(
        kp.public,
        X509EncodedKeySpec::class.java
    )
    val base64Encode = Base64.getEncoder().encodeToString(spec.encoded);

    return jwsObject.serialize()
}

fun getNewKeyPair(): KeyPair {
    val kpg = KeyPairGenerator.getInstance("RSA")
    kpg.initialize(2048)
    return kpg.generateKeyPair()
}
