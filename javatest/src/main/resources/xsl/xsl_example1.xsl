<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>

                <xsl:call-template name="number">
                    <xsl:with-param name="x" select="catalog/cd/quantity"/>
                </xsl:call-template>

            </body>
        </html>
    </xsl:template>

    <xsl:template name="number">
        <xsl:param name="x"/>
        <xsl:if test="$x!=''">
            <xsl:choose>
                <xsl:when test="contains($x,'E')">
                    <xsl:variable name="newX">
                        <xsl:variable name="vExponent" select="substring-after($x,'E')"/>
                        <xsl:variable name="vMantissa" select="substring-before($x,'E')"/>
                        <xsl:choose>
                            <xsl:when test="starts-with($vExponent,'-')">
                                <xsl:variable name="vFactor"
                                              select="substring('100000000000000000000000000000000000000000000',
									1, number(substring($vExponent,2)) + 1)"/>
                                <xsl:value-of select="number($vMantissa) div number($vFactor)"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:variable name="vFactor"
                                              select="substring('100000000000000000000000000000000000000000000',
									1, number($vExponent) + 1)"/>
                                <xsl:value-of select="number($vMantissa) * number($vFactor)"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>
                    <xsl:value-of select="format-number($newX,'0.00')"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$x"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>