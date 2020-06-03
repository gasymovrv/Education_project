package com.javapro.learn.kotlintest.examples.receipts.utils

import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.stream.Collectors

fun main() {
//    ean8("46198488")
//    ean13("4606203090785")
//    itf14("14601234567890")
//    gs1("010460406000600021N4N57RSCBUZTQ24030040029101612181724010191ffd092tIAF/YVoU4roQS3M/m4z78yFq0fc/WsSmLeX5QkF/YVWwy8IMYAeiQ91Xa2z/fFSJcOkb2N+uUUmfr4n0mOX0Q==")
//    rf("RU-401301-AAA02770301")
//    tnved("2710124190")
    //AAUAAALAiXRUVDN2WVRy
    decodeAndPrint("RE0ENgOJOfxBR21oSjJOMXQ1WnZq")
}

fun ean8(codeEan8: String) {
    printSummary("EAN-8", codeEan8)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))

    val hex = "4508" + StringUtils.leftPad(codeEan8.toLong().toString(16), 12, "00")
    printHex(hex)

    val bytes: ByteArray = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    decodeAndPrint(base64)
}

fun ean13(codeEan13: String) {
    printSummary("EAN-13", codeEan13)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))

    val hex = "450d" + StringUtils.leftPad(codeEan13.toLong().toString(16), 12, "00")
    printHex(hex)

    val bytes: ByteArray = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    decodeAndPrint(base64)
}

fun itf14(codeItf14: String) {
    printSummary("ITF-14", codeItf14)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))

    val hex = "490e" + StringUtils.leftPad(codeItf14.toLong().toString(16), 12, "00")
    printHex(hex)

    val bytes: ByteArray = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    decodeAndPrint(base64)
}

fun gs1(codeGs1: String) {
    printSummary("GS1", codeGs1)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))

    val gtin = codeGs1.substring(2, 16).toLong()
    val sn = codeGs1.substring(18)
    val snHex = sn.chars()
            .mapToObj { it.toString(16) }
            .collect(Collectors.joining(""))
    val hex = ("444d" + StringUtils.leftPad(gtin.toString(16), 12, "00")) + snHex
    printHex(hex)

    val bytes = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    decodeAndPrint(base64)
}

fun rf(codeRf: String) {
    printSummary("Изделия из натурального меха (RF)", codeRf)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))
    val snHex = codeRf.chars()
            .mapToObj { it.toString(16) }
            .collect(Collectors.joining(""))
    val hex = "5246$snHex"
    printHex(hex)

    val bytes = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    println("Потребуется другой алгоритм")
    println()
//    val decodedBytes: ByteArray = Base64.getDecoder().decode(base64)
//    val encodedString = Hex.encodeHexString(decodedBytes)
//    println(encodedString.substring(0, 4))
//    val sb = StringBuilder()
//    var i = 10
//    while (i < 18) {
//        sb.append(Hex.decodeHex(encodedString.substring(i, i + 2))[0].toChar())
//        i = i + 2
//    }
//    println(sb.toString())
}

fun tnved(tnved: String) {
    printSummary("ТН ВЭД", tnved)
    println("-".repeat(10) + " ENCODE " + "-".repeat(10))
    val hex = "4541" + StringUtils.leftPad(tnved.toLong().toString(16), 12, "00")
    printHex(hex)

    val bytes = Hex.decodeHex(hex)
    printBytes(bytes)

    val base64: String = Base64.getEncoder().encodeToString(bytes)
    printBase64(base64)

    println("-".repeat(10) + " DECODE " + "-".repeat(10))
    decodeAndPrint(base64)
}

fun decodeAndPrint(base64: String) {
    val bytes: ByteArray = Base64.getDecoder().decode(base64)
    val encodedString: String = Hex.encodeHexString(bytes)

    val hexFrom0to4 = encodedString.substring(0, 4)
    val hexFrom4to16 = encodedString.substring(4, 16)

    println("${hexFrom0to4.withSpaces(2)} - первые 2 байта в hex")
    println("${hexFrom4to16.withSpaces(2)} - с 3 по 8 байт в hex")
    println("${hexFrom4to16.toLong(16)} - с 3 по 8 байт в dec")
    println()
}

fun printSummary(format:String, code: String) {
    println("-".repeat(20) + " $format " + "-".repeat(20))
    println("productCode: $code")
}

fun String.withSpaces(interval: Int): String {
    var formatted = ""
    var nextInsert = interval
    this.toCharArray().forEachIndexed { index, c ->
        formatted += if (index+1 == nextInsert) {
            nextInsert += interval
            "$c "
        } else "$c"
    }
    return formatted
}

fun printBytes(input: ByteArray) {
    println("${input.contentToString()} - bytes")
}
fun printBase64(input: String) {
    println("$input - bytes as base64 string")
}
fun printHex(input: String) {
    println("${input.withSpaces(2)} - hex string")
}