<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://zakupki.gov.ru/oos/printform/1" xmlns:oos="http://zakupki.gov.ru/oos/types/1" version="1.0" exclude-result-prefixes="ns0 oos">

    <xsl:output version="4.01" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
    <xsl:template match="ns0:fcsNotificationOrgChange">
        <html>
            <head>
                <title>Печатная форма уведомления об изменении организации, осуществляющей размещение</title>
                <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
                <style type="text/css">
                    p.subtitle {
                    text-align: center;
                    }
                    p.title{
                    text-align: center;
                    font-size: 20px;
                    padding-top:20px;
                    font-weight: bold;
                    }
                    p.titlebottom{
                    font-style: italic;
                    text-align: center;
                    padding-bottom:20px;
                    }
                    .table{
                    border-collapse: collapse;
                    }
                    .table td {
                    padding: 5px 5px;
                    border-style: solid;
                    border-width: 1px;
                    border-color: black;
                    }
                    td {
                    padding: 0px 10px 0px 15px;
                    }
                </style>
            </head>
            <body>
                <xsl:call-template name="header">
                    <xsl:with-param name="title">Уведомление об изменении организации, осуществляющей размещение
                    </xsl:with-param>
                </xsl:call-template>
                <p align="center">
                    <xsl:call-template name="singleChangeType">
                        <xsl:with-param name="param1" select="'(В связи с '"/>
                        <xsl:with-param name="param2" select="')'"/>
                        <xsl:with-param name="value" select="oos:baseChange/oos:changeType"/>
                    </xsl:call-template>
                </p>
                <xsl:element name="table">
                    <tr>
                        <th style="width:40%;"/>
                        <th style="width:60%;"/>
                    </tr>
                    <xsl:call-template name="singleRecordDefault">
                        <xsl:with-param name="param" select="'Наименование объекта закупки'"/>
                        <xsl:with-param name="value" select="oos:purchase/oos:purchaseObjectInfo"/>
                        <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                    </xsl:call-template>
                    <xsl:call-template name="singleRecordDefault">
                        <xsl:with-param name="param" select="'Дополнительная информация об изменении организации, осуществляющей размещение '"/>
                        <xsl:with-param name="value" select="oos:baseChange/oos:addInfo"/>
                        <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        <xsl:with-param name="multiline" select="true()"/>
                    </xsl:call-template>
                    <xsl:call-template name="singleRecordRole">
                        <xsl:with-param name="param" select="'Предыдущая организация, осуществляющая размещение: '"/>
                        <xsl:with-param name="value" select="oos:previousRespOrg/oos:responsibleRole"/>
                        <xsl:with-param name="name" select="oos:previousRespOrg/oos:fullName"/>
                    </xsl:call-template>
                    <xsl:call-template name="singleRecordRole">
                        <xsl:with-param name="param" select="'Новая организация, осуществляющая размещение: '"/>
                        <xsl:with-param name="value" select="oos:newRespOrg/oos:responsibleRole"/>
                        <xsl:with-param name="name" select="oos:newRespOrg/oos:fullName"/>
                    </xsl:call-template>
                    <xsl:call-template name="documentPublishDate"/>
                    <xsl:call-template name="singleRecordRole">
                        <xsl:with-param name="param" select="'Организация, осуществляющая размещение уведомления'"/>
                        <xsl:with-param name="value" select="oos:notifRespOrg/oos:responsibleRole"/>
                        <xsl:with-param name="name" select="oos:notifRespOrg/oos:fullName"/>
                    </xsl:call-template>
                    <br/>
                </xsl:element>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="header">
        <xsl:param name="title"/>
        <p class="title">
            <xsl:value-of select="$title"/>
        </p>
        <p class="subtitle">
            <xsl:if test="oos:docPublishDate/text()!=''">
                <xsl:value-of select="oos:docNumber"/>
                <xsl:if test="count(oos:docPublishDate)&gt;0">
                    <xsl:text> от </xsl:text>
                    <xsl:call-template name="dateWithoutTime">
                        <xsl:with-param name="dateTime" select="oos:docPublishDate"/>
                    </xsl:call-template>
                </xsl:if>
            </xsl:if>
            <xsl:if test="oos:purchase/oos:purchaseNumber/text()!=''">
                <xsl:text> для закупки №</xsl:text>
                <xsl:value-of select="oos:purchase/oos:purchaseNumber"/>
            </xsl:if>
        </p>
    </xsl:template>

    <xsl:template name="documentPublishDate">
        <xsl:variable name="docPublishTitle">
            <xsl:text>Размещено: </xsl:text>
        </xsl:variable>
        <xsl:call-template name="singleRecordDefault">
            <xsl:with-param name="param" select="$docPublishTitle"/>
            <xsl:with-param name="value" select="oos:docPublishDate"/>
            <xsl:with-param name="dateType" select="'datetime'"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="dateWithoutTime">
        <xsl:param name="dateTime"/>
        <xsl:variable name="date" select="substring($dateTime,1,10 )"/>
        <xsl:variable name="year" select="substring-before($date, '-')"/>
        <xsl:variable name="month" select="substring-before(substring-after($date, '-'), '-')"/>
        <xsl:variable name="day" select="substring-after(substring-after($date, '-'), '-')"/>
        <xsl:if test="$dateTime!=''">
            <xsl:value-of select="concat($day, '.', $month, '.', $year)"/>
        </xsl:if>
    </xsl:template>

    <xsl:template name="dateWithTime">
        <xsl:param name="dateTime"/>
        <xsl:variable name="date" select="substring($dateTime,1,10 )"/>
        <xsl:variable name="year" select="substring-before($date, '-')"/>
        <xsl:variable name="month" select="substring-before(substring-after($date, '-'), '-')"/>
        <xsl:variable name="day" select="substring-after(substring-after($date, '-'), '-')"/>
        <xsl:variable name="hour" select="substring($dateTime,12,2 )"/>
        <xsl:variable name="minute" select="substring($dateTime,15,2 )"/>
        <xsl:if test="$dateTime!=''">
            <xsl:value-of select="concat($day, '.', $month, '.', $year, ' ', $hour, ':', $minute)"/>
        </xsl:if>
    </xsl:template>

    <xsl:template name="printDefault">
        <xsl:param name="value" select="''"/>
        <xsl:param name="msg" select="''"/>
        <xsl:param name="dateType" select="''"/>
        <xsl:choose>
            <xsl:when test="$value!=''">
                <xsl:choose>
                    <xsl:when test="$dateType='date'">
                        <xsl:call-template name="dateWithoutTime">
                            <xsl:with-param name="dateTime" select="$value"/>
                        </xsl:call-template>
                    </xsl:when>
                    <xsl:when test="$dateType='datetime'">
                        <xsl:call-template name="dateWithTime">
                            <xsl:with-param name="dateTime" select="$value"/>
                        </xsl:call-template>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$value"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$msg"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="singleRecordDefault">
        <xsl:param name="param" select="''"/>
        <xsl:param name="value" select="''"/>
        <xsl:param name="msg" select="''"/>
        <xsl:param name="dateType" select="''"/>
        <xsl:param name="optional" select="false()"/>
        <xsl:param name="multiline" select="false()"/>
        <xsl:choose>
            <xsl:when test="$value='' and $optional=true()">
            </xsl:when>
            <xsl:otherwise>
                <tr>
                    <td>
                        <p class="parameter">
                            <xsl:value-of select="$param"/>
                        </p>
                    </td>
                    <td>
                        <xsl:choose>
                            <xsl:when test="$multiline">
                                <xsl:call-template name="replaceNL">
                                    <xsl:with-param name="pText" select="$value"/>
                                </xsl:call-template>
                            </xsl:when>
                            <xsl:otherwise>
                                <p class="parameterValue">
                                    <xsl:call-template name="printDefault">
                                        <xsl:with-param name="value" select="$value"/>
                                        <xsl:with-param name="msg" select="$msg"/>
                                        <xsl:with-param name="dateType" select="$dateType"/>
                                    </xsl:call-template>
                                </p>
                            </xsl:otherwise>
                        </xsl:choose>
                    </td>
                </tr>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="replaceNL">
        <xsl:param name="pText" select="''"/>

        <xsl:choose>
            <xsl:when test="contains($pText, '&#10;')">
                <xsl:value-of select="substring-before($pText, '&#10;')"/>
                <br/>
                <xsl:call-template name="replaceNL">
                    <xsl:with-param name="pText" select="substring-after($pText, '&#10;')"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$pText"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="headerCaption">
        <xsl:param name="caption"/>
        <tr>
            <td colspan="2">
                <p class="caption">
                    <b>
                        <xsl:value-of select="$caption"/>
                    </b>
                </p>
            </td>
        </tr>
    </xsl:template>

    <xsl:template name="valueRight">
        <xsl:param name="value" select="''"/>
        <tr>
            <td/>
            <td align="right">
                <xsl:value-of select="$value"/>
            </td>
        </tr>
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
                                <xsl:variable name="vFactor" select="substring('100000000000000000000000000000000000000000000',          1, substring($vExponent,2) + 1)"/>
                                <xsl:value-of select="$vMantissa div $vFactor"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:variable name="vFactor" select="substring('100000000000000000000000000000000000000000000',          1, $vExponent + 1)"/>
                                <xsl:value-of select="$vMantissa * $vFactor"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>
                    <xsl:value-of select="format-number($newX,'0.00')"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="format-number($x,'0.00')"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

    <xsl:template name="Attachments">
        <tr>
            <td>
                <b>Перечень прикрепленных документов</b>
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="count(oos:attachments/oos:attachment)&gt;0">
                        <xsl:for-each select="oos:attachments/oos:attachment">
                            <p>
                                <xsl:value-of select="position()"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="oos:fileName/text()"/>
                            </p>
                        </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>Документы не прикреплены</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </td>
        </tr>
    </xsl:template>
    <xsl:template name="singleRecordRole">
        <xsl:param name="param" select="''"/>
        <xsl:param name="value" select="''"/>
        <xsl:param name="name" select="''"/>
        <xsl:variable name="role">
            <xsl:choose>
                <xsl:when test="$value = 'CU'">Заказчик</xsl:when>
                <xsl:when test="$value = 'OCU'">Заказчик в качестве Организатора совместного конкурса</xsl:when>
                <xsl:when test="$value = 'RA'">Уполномоченный орган</xsl:when>
                <xsl:when test="$value = 'ORA'">Уполномоченный орган в качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:when test="$value = 'AI'">Уполномоченное учреждение</xsl:when>
                <xsl:when test="$value = 'OAI'">Уполномоченное учреждение в качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:when test="$value = 'OA'">Организация, осуществляющая полномочия заказчика на осуществление закупок
                    на основании договора (соглашения) в соответствии с частью 6 статьи 15 Федерального закона № 44-ФЗ
                </xsl:when>
                <xsl:when test="$value = 'OOA'">Организация, осуществляющая полномочия заказчика на осуществление
                    закупок на основании договора (соглашения) в соответствии с частью 6 статьи 15 Федерального закона №
                    44-ФЗ, в качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:when test="$value = 'CS'">Заказчик, осуществляющий закупки в соответствии с частью 5 статьи 15
                    Федерального закона № 44-ФЗ
                </xsl:when>
                <xsl:when test="$value = 'OCS'">Заказчик, осуществляющий закупки в соответствии с частью 5 статьи 15
                    Федерального закона № 44-ФЗ в качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:when test="$value = 'CC'">Заказчик, осуществляющий закупки в соответствии с Федеральным законом №
                    44-ФЗ, в связи с неразмещением положения о закупке в соответствии с положениями Федерального закона
                    № 223-ФЗ
                </xsl:when>
                <xsl:when test="$value = 'OCC'">Заказчик, осуществляющий закупки в соответствии с Федеральным законом №
                    44-ФЗ, в связи с неразмещением положения о закупке в соответствии с положениями Федерального закона
                    № 223-ФЗ в качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:when test="$value = 'AU'">Заказчик, осуществляющий закупку на проведение обязательного аудита
                </xsl:when>
                <xsl:when test="$value = 'OAU'">Заказчик, осуществляющий закупку на проведение обязательного аудита в
                    качестве Организатора совместного конкурса
                </xsl:when>
                <xsl:otherwise>Информация отсутствует</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <tr>
            <td>
                <p class="parameter">
                    <xsl:value-of select="$param"/>
                </p>
            </td>
            <td>
                <p class="parameterValue">
                    <xsl:value-of select="$role"/>
                    <br/>
                    <xsl:value-of select="$name"/>
                </p>
            </td>
        </tr>
    </xsl:template>
    <xsl:template name="singleChangeType">
        <xsl:param name="param1" select="''"/>
        <xsl:param name="param2" select="''"/>
        <xsl:param name="value" select="''"/>
        <xsl:variable name="role">
            <xsl:choose>
                <xsl:when test="$value = 'C'">прекращением действия права уполномоченного органа, уполномоченного
                    учреждения, организации, осуществляющей полномочия заказчика на осуществление закупок на основании
                    договора (соглашения) в соответствии с частью 6 статьи 15 Федерального закона № 44-ФЗ на размещение
                    закупок для заказчика</xsl:when>
                <xsl:when test="$value = 'R'">реорганизацией заказчика или уполномоченного органа, осуществляющего размещение</xsl:when>
                <xsl:when test="$value = 'F'">изменением организации, осуществляющей размещение в соответствии со
                    статьей 26 Федерального закона № 44-ФЗ</xsl:when>
                <xsl:otherwise>Информация отсутствует</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <tr align="center">
            <xsl:value-of select="$param1"/>
            <xsl:value-of select="$role"/>
            <xsl:value-of select="$param2"/>
        </tr>
    </xsl:template>
</xsl:stylesheet>
