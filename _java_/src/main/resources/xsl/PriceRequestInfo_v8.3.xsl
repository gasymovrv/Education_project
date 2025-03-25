<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://zakupki.gov.ru/oos/printform/1"
                xmlns:fcs="http://zakupki.gov.ru/oos/types/1">


    <xsl:output version="4.0" method="html" indent="no" encoding="UTF-8"
                doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN"
                doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>

    <xsl:template match="ns0:fcsRequestForQuotation">
        <html>
            <head>
                <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
                <title>Печатная форма</title>

                <style type="text/css">
                    body {
                    padding: 10px 0 10px 0;
                    font: normal 14px Tahoma, Geneva, sans-serif;
                    }

                    h1, .h1 {
                    font-size: 14px;
                    font-weight: bold;
                    }

                    .centered-up {
                    text-align: center;
                    vertical-align: top;
                    }

                    .centered-both {
                    text-align: center;
                    }

                    .centered-both-bold {
                    text-align: center;
                    font-weight: bold;
                    }

                    .centered-both {
                    text-align: center;
                    vertical-align: top;
                    }

                    .pfStrong {
                    font-weight: bold;
                    }

                    .pfTable {
                    border-collapse:collapse;
                    }

                    .pfTableColumns td{
                    font-size: 12px;
                    text-align: left;
                    border-collapse:collapse;
                    padding:10px 10px;
                    }

                    .left {
                    text-align: left;
                    }

                    .table{
                    border-collapse:collapse;
                    }
                    .table td{
                    padding:5px 5px;
                    border-style:solid;
                    border-width:1px;
                    border-color:black;
                    }
                    .table td.innerTableHolder{
                    padding: 0px;
                    height: 1px;
                    }
                    .table th{
                    padding:5px 5px;
                    border-style:solid;
                    border-width:1px;
                    border-color:black;
                    }
                    .ktruTable {
                    height: 100%;
                    width: 100%;
                    }
                    .ktruTable td {
                    width:33%;
                    }
                    .ktruTable tr:first-of-type td {
                    border-top: none;
                    }
                    .ktruTable tr:last-of-type td {
                    border-bottom: none;
                    }
                    .ktruTable tr td:first-of-type {
                    border-left: none;
                    }
                    .ktruTable tr td:last-of-type {
                    border-right: none;
                    width: *;
                    }
                    tr.characteristicsTitle th {
                    width: 33%;
                    }
                    tr.characteristicsTitle th:last-of-type {
                    width: *;
                    }
                    .ktruTable td{
                    padding:0px 10px 0px 15px;
                    }
                    .font9 {
                    font-size:9px
                    }

                </style>
            </head>

            <body>
                <h1 class="centered-up">
                    <xsl:choose>
                        <xsl:when test="fcs:modificationReason/text()!=''">
                            <xsl:text>Изменение запроса цен </xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:text>Запрос цен </xsl:text>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:if test="fcs:registryNum">
                        <xsl:value-of select="fcs:registryNum"></xsl:value-of>
                        <xsl:if test="fcs:versionNumber">
                            <xsl:text> (ред. №</xsl:text>
                            <xsl:value-of select="fcs:versionNumber"></xsl:value-of>
                            <xsl:text>)</xsl:text>
                        </xsl:if>
                    </xsl:if>
                </h1>
                <br/>
                <div>
                    <table class="pfTable pfTableColumns">
                        <tr>
                            <th width="50%"/>
                            <th width="50%"/>
                        </tr>
                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Общая информация запроса цен:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Номер запроса'"/>
                            <xsl:with-param name="value" select="fcs:registryNum"/>
                            <xsl:with-param name="msg"
                                            select="'Генерируется автоматически системой после размещения запроса цен'"/>
                        </xsl:call-template>
                        <xsl:call-template name="tableRow">
                            <xsl:with-param name="header" select="'Статус'"/>
                            <xsl:with-param name="text" select="fcs:state"/>
                        </xsl:call-template>
                        <xsl:call-template name="tableRow2">
                            <xsl:with-param name="header"
                                            select="'Наименование организации, осуществляющей размещение запроса цен'"/>
                            <xsl:with-param name="text" select="fcs:publishOrg/fcs:shortName"/>
                            <xsl:with-param name="value" select="fcs:publishOrg/fcs:responsibleRole"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Наименование объекта закупки'"/>
                            <xsl:with-param name="value" select="fcs:requestObjectInfo"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:if test="fcs:versionNumber>1">
                            <xsl:call-template name="singleRecordDefault">
                                <xsl:with-param name="param" select="'Основание изменения запроса цен'"/>
                                <xsl:with-param name="value" select="fcs:modificationReason"/>
                                <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                            </xsl:call-template>
                        </xsl:if>
                        <br/>
                        <tr>
                            <td colspan="2">
                                <p style="font-style:italic">
                                <xsl:text>Проведение данной процедуры сбора информации не влечет за собой возникновения каких-либо обязательств заказчика.
                                </xsl:text>
                                </p>
                                <p style="font-style:italic">
                                <xsl:text>Из ответа на запрос должны однозначно определяться цена единицы товара, работы, услуги и общая цена контракта на условиях, указанных в запросе, срок действия предлагаемой цены, расчет такой цены с целью предупреждения намеренного завышения или занижения цен товаров, работ, услуг
                                </xsl:text>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Место предоставления ценовой информации, контактная информация:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Место предоставления ценовой информации'"/>
                            <xsl:with-param name="value" select="fcs:responsibleInfo/fcs:place"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Ответственное должностное лицо, осуществляющее сбор ценовой информации'"/>
                            <xsl:with-param name="value"
                                            select="normalize-space(concat(fcs:responsibleInfo/fcs:contactPerson/fcs:lastName, ' ', fcs:responsibleInfo/fcs:contactPerson/fcs:firstName, ' ', fcs:responsibleInfo/fcs:contactPerson/fcs:middleName))"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Адрес электронной почты'"/>
                            <xsl:with-param name="value" select="fcs:responsibleInfo/fcs:contactEMail"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Номер контактного телефона'"/>
                            <xsl:with-param name="value" select="fcs:responsibleInfo/fcs:contactPhone"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Факс'"/>
                            <xsl:with-param name="value" select="fcs:responsibleInfo/fcs:contactFax"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Дополнительная информация'"/>
                            <xsl:with-param name="value" select="fcs:responsibleInfo/fcs:addInfo"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Сроки предоставления ценовой информации:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Дата и время начала предоставления ценовой информации (по местному времени)'"/>
                            <xsl:with-param name="value" select="fcs:procedureInfo/fcs:request/fcs:startDate"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                            <xsl:with-param name="dateType" select="'datetime'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Дата и время окончания предоставления ценовой информации (по местному времени)'"/>
                            <xsl:with-param name="value" select="fcs:procedureInfo/fcs:request/fcs:endDate"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                            <xsl:with-param name="dateType" select="'datetime'"/>
                        </xsl:call-template>

                        <tr>
                            <td>
                                <xsl:text>Предполагаемые сроки проведения закупки</xsl:text>
                            </td>
                            <td>
                                <p class="parameterValue">
                                    <xsl:choose>
                                        <xsl:when test="fcs:procedureInfo/fcs:purchase/fcs:startDate">
                                            <xsl:call-template name="dateWithoutDay">
                                                <xsl:with-param name="dateTime"
                                                                select="fcs:procedureInfo/fcs:purchase/fcs:startDate"/>
                                            </xsl:call-template>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:text>ММ.ГГГГ</xsl:text>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <xsl:text> - </xsl:text>
                                    <xsl:choose>
                                        <xsl:when test="fcs:procedureInfo/fcs:purchase/fcs:endDate">
                                            <xsl:call-template name="dateWithoutDay">
                                                <xsl:with-param name="dateTime"
                                                                select="fcs:procedureInfo/fcs:purchase/fcs:endDate"/>
                                            </xsl:call-template>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:text>ММ.ГГГГ</xsl:text>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Сведения об объекте закупки / сведения о товарах, работах, услугах:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Описание объекта закупки '"/>
                            <xsl:with-param name="value" select="fcs:products/fcs:objectInfo"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                    </table>

                    <br/>
                    <br/>
                    <table class="table font9">
                        <tr>
                            <th rowspan="2">Наименование товара, работы, услуги по КТРУ</th>
                            <th rowspan="2">Код позиции</th>
                            <th colspan="3">Характеристики товара, работы, услуги</th>
                            <th rowspan="2">Единица измерения</th>
                            <th rowspan="2">Количество</th>
                        </tr>
                        <tr class="characteristicsTitle">
                            <th>Наименование</th>
                            <th>Значение</th>
                            <th>Единица измерения</th>
                        </tr>
                        <xsl:for-each select="fcs:products/fcs:product">
                            <tr>
                                <td>
                                    <xsl:value-of select="fcs:name"/>
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="fcs:KTRU != ''">
                                            <xsl:value-of select="fcs:KTRU/fcs:code"/>
                                        </xsl:when>
                                        <xsl:when test="fcs:OKPD2 != ''">
                                            <xsl:value-of select="fcs:OKPD2/fcs:code"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="fcs:OKPD/fcs:code"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                                <td colspan="3" class="innerTableHolder">
                                    <table class="ktruTable">
                                        <xsl:choose>
                                            <xsl:when test="fcs:KTRU != ''">
                                                <xsl:if test="count(fcs:KTRU/fcs:characteristics/fcs:characteristicsUsingReferenceInfo) > 0">
                                                    <xsl:for-each select="fcs:KTRU/fcs:characteristics/fcs:characteristicsUsingReferenceInfo">
                                                        <xsl:call-template name="characteristicBlock"/>
                                                    </xsl:for-each>
                                                </xsl:if>

                                                <xsl:if test="count(fcs:KTRU/fcs:characteristics/fcs:characteristicsUsingTextForm) > 0">
                                                    <xsl:for-each select="fcs:KTRU/fcs:characteristics/fcs:characteristicsUsingTextForm">
                                                        <xsl:call-template name="characteristicBlock"/>
                                                    </xsl:for-each>
                                                </xsl:if>
                                            </xsl:when>
                                            <xsl:when test="fcs:OKPD2 != ''">
                                                <xsl:if test="count(fcs:OKPD2/fcs:characteristics/fcs:characteristicsUsingTextForm) > 0">
                                                    <xsl:for-each select="fcs:OKPD2/fcs:characteristics/fcs:characteristicsUsingTextForm">
                                                        <xsl:call-template name="characteristicBlock"/>
                                                    </xsl:for-each>
                                                </xsl:if>
                                            </xsl:when>
                                        </xsl:choose>
                                    </table>
                                </td>
                                <td>
                                    <xsl:value-of select="fcs:OKEI/fcs:name"/>
                                </td>
                                <td>
                                    <xsl:call-template name="number">
                                        <xsl:with-param name="x" select="fcs:quantity"/>
                                    </xsl:call-template>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>

                    <br/>
                    <br/>

                    <!-- Old positions -->
                    <!--<xsl:call-template name="Customer_LK"/>-->
                    <!--<br/>-->
                    <!--<br/>-->

                    <table class="pfTable pfTableColumns">
                        <tr>
                            <th width="50%"/>
                            <th width="50%"/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Сведения, определяющие идентичность или однородность товара, работы, услуги'"/>
                            <xsl:with-param name="value" select="fcs:products/fcs:identity"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>

                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Требования к условиям исполнения контракта:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Основные условия исполнения контракта, заключаемого по результатам закупки'"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:main"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Порядок оплаты'"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:payment"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Размер обеспечения исполнения контракта'"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:contractGuarantee"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Требования к гарантийному сроку товара, работы, услуги и (или) объему предоставления гарантий их качества'"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:warranty"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param"
                                            select="'Требования к порядку поставки товаров, выполнению работ, оказанию услуг '"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:delivery"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <xsl:call-template name="singleRecordDefault">
                            <xsl:with-param name="param" select="'Дополнительная информация '"/>
                            <xsl:with-param name="value" select="fcs:conditions/fcs:addInfo"/>
                            <xsl:with-param name="msg" select="'Информация отсутствует'"/>
                        </xsl:call-template>
                        <tr>
                            <td class="pfStrong" colspan="2">
                                <xsl:text>Приложения:</xsl:text>
                            </td>
                            <td/>
                        </tr>
                        <!-- Перечень прикрепленных документов -->
                        <xsl:call-template name="Attachments"/>
                    </table>
                    <p align="center">
                        <xsl:call-template name="singleChangeType">
                            <xsl:with-param name="param1" select="'(В связи с '"/>
                            <xsl:with-param name="param2" select="')'"/>
                            <xsl:with-param name="value" select="fcs:changeType"/>
                        </xsl:call-template>
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="Attachments">
        <tr>
            <td colspan="2">
                <xsl:choose>
                    <xsl:when test="count(fcs:attachments/fcs:attachment)>0">
                        <xsl:for-each select="fcs:attachments/fcs:attachment">
                            <p>
                                <xsl:value-of select="position()"/>
                                <xsl:text> </xsl:text>
                                <xsl:choose>
                                    <xsl:when test="fcs:docDescription/text()!=''">
                                        <xsl:value-of select="fcs:docDescription/text()"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="fcs:fileName/text()"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                        Приложения отсутствуют
                    </xsl:otherwise>
                </xsl:choose>
            </td>
        </tr>
    </xsl:template>

    <xsl:template name="Customer_LK">
        <table class="pfTable pfTableColumns">
            <xsl:variable name="valuePresentOkpd">
                <xsl:value-of select="false()"/>
                <xsl:for-each select="fcs:products/fcs:product">
                    <xsl:if test="fcs:OKPD/fcs:code/text()!=''">
                        <xsl:value-of select="true()"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:variable>
            <tr style="border-bottom: 1px solid black; border-top: 1px solid black;">
                <td style="padding: 10px;">Наименование товара, работы, услуги по КТРУ</td>
                <td style="padding: 10px;">Код позиции</td>
                <td style="padding: 10px;">Единицы измерения</td>
                <td style="padding: 10px;">Количество</td>
            </tr>
            <xsl:if test="count(fcs:products/fcs:product)>0">
                <xsl:for-each select="fcs:products/fcs:product">
                    <tr>
                        <td style="padding: 10px;">
                            <xsl:value-of select="fcs:name/text()"/>
                        </td>
                        <td style="padding: 10px;">
                            <xsl:choose>
                                <xsl:when test="$valuePresentOkpd = 'false'">
                                    <xsl:value-of select="fcs:OKPD2/fcs:code/text()"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="fcs:OKPD/fcs:code/text()"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                        <td style="padding: 10px;">
                            <xsl:value-of select="fcs:OKEI/fcs:name/text()"/>
                        </td>
                        <td style="padding: 10px;">
                            <xsl:value-of select="fcs:quantity/text()"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:if>
        </table>
        <xsl:if test="count(fcs:products/fcs:product)=0">
            <xsl:text>Информация отсутствует</xsl:text>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tableRow">
        <xsl:param name="header" select="''"/>
        <xsl:param name="text" select="''"/>
        <tr>
            <td>
                <xsl:value-of select="$header"/>
            </td>
            <td>
                <xsl:value-of select="$text"/>
            </td>
        </tr>
    </xsl:template>
    <xsl:template name="tableRow2">
        <xsl:param name="header" select="''"/>
        <xsl:param name="text" select="''"/>
        <xsl:param name="value" select="''"/>

        <xsl:variable name="role">
            <xsl:choose>
                <xsl:when test="$value = 'CU'">Заказчик</xsl:when>
                <xsl:when test="$value = 'OCU'">Заказчик в качестве Организатора совместного конкурса</xsl:when>
                <xsl:when test="$value = 'RA'">Уполномоченный орган</xsl:when>
                <xsl:when test="$value = 'ORA'">Уполномоченный орган в качестве Организатора совместного конкурса</xsl:when>
                <xsl:when test="$value = 'AI'">Уполномоченное учреждение</xsl:when>
                <xsl:when test="$value = 'OAI'">Уполномоченное учреждение в качестве Организатора совместного конкурса</xsl:when>
                <xsl:when test="$value = 'OA'">Организация, осуществляющая полномочия заказчика на осуществление закупок на основании договора (соглашения) в соответствии с частью 6 статьи 15 Федерального закона № 44-ФЗ</xsl:when>
                <xsl:when test="$value = 'OOA'">Организация, осуществляющая полномочия заказчика на осуществление закупок на основании договора (соглашения) в соответствии с частью 6 статьи 15 Федерального закона № 44-ФЗ, в качестве Организатора совместного конкурса</xsl:when>
            </xsl:choose>
        </xsl:variable>

        <tr>
            <td>
                <xsl:value-of select="$header"/>
            </td>
            <td>
                <xsl:value-of select="$text"/><br/>
                <xsl:value-of select="$role"/>
            </td>
        </tr>

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

    <xsl:template name="dateWithoutDay">
        <xsl:param name="dateTime"/>
        <xsl:variable name="date" select="substring($dateTime,1,10 )"/>
        <xsl:variable name="year" select="substring-before($date, '-')"/>
        <xsl:variable name="month" select="substring-before(substring-after($date, '-'), '-')"/>
        <xsl:if test="$dateTime!=''">
            <xsl:value-of select="concat($month, '.', $year)"/>
        </xsl:if>
    </xsl:template>

    <xsl:template name="singleRecordDefault">
        <xsl:param name="param" select="''"/>
        <xsl:param name="value" select="''"/>
        <xsl:param name="msg" select="''"/>
        <xsl:param name="dateType" select="''"/>
        <xsl:param name="optional" select="false()"/>
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
                        <p class="parameterValue">
                            <xsl:call-template name="printDefault">
                                <xsl:with-param name="value" select="$value"/>
                                <xsl:with-param name="msg" select="$msg"/>
                                <xsl:with-param name="dateType" select="$dateType"/>
                            </xsl:call-template>
                        </p>
                    </td>
                </tr>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <xsl:template name="convertNumberDate">
        <xsl:param name="header" select="''"/>
        <xsl:param name="number"/>
        <xsl:param name="date"/>
        <tr>
            <td>
                <xsl:value-of select="$header"/>
            </td>
            <td>
                <xsl:value-of select="$number"/>
                <xsl:text> от </xsl:text>
                <xsl:call-template name="formatDate">
                    <xsl:with-param name="dateTime" select="$date"/>
                </xsl:call-template>
            </td>
        </tr>
    </xsl:template>

    <xsl:template name="convertTextDateNumber">
        <xsl:param name="header" select="''"/>
        <xsl:param name="text"/>
        <xsl:param name="number"/>
        <xsl:param name="date"/>
        <xsl:param name="name"/>
        <tr>
            <td>
                <xsl:value-of select="$header"/>
            </td>
            <td>
                <xsl:if test="$text">
                    <xsl:value-of select="$text"/>
                    <br/>
                </xsl:if>
                <xsl:text>от </xsl:text>
                <xsl:call-template name="formatDate">
                    <xsl:with-param name="dateTime" select="$date"/>
                </xsl:call-template>
                <xsl:text> № </xsl:text>
                <xsl:value-of select="$number"/>
                <xsl:if test="$name">
                    <br/>
                    <xsl:value-of select="$name"/>
                </xsl:if>
            </td>
        </tr>
    </xsl:template>


    <xsl:template name="formatDate">
        <xsl:param name="dateTime"/>
        <xsl:variable name="date" select="substring-before($dateTime, 'T')"/>
        <xsl:variable name="year" select="substring-before($date, '-')"/>
        <xsl:variable name="month" select="substring-before(substring-after($date, '-'), '-')"/>
        <xsl:variable name="day" select="substring-after(substring-after($date, '-'), '-')"/>
        <xsl:if test="$dateTime!=''">
            <xsl:value-of select="concat($day, '.', $month, '.', $year)"/>
        </xsl:if>
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
                    <xsl:value-of select="$newX"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$x"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

    <xsl:template name="characteristicBlock">
        <xsl:if test="fcs:values!=''">
            <xsl:variable name="characteristicName" select="fcs:name"/>
            <xsl:variable name="characteristicType" select="fcs:type"/>
            <xsl:for-each select="fcs:values/fcs:value">
                <tr>
                    <xsl:if test="position()=1">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="last()"/>
                            </xsl:attribute>
                            <xsl:value-of select="$characteristicName"/>
                        </td>
                    </xsl:if>
                    <td>
                        <xsl:choose>
                            <xsl:when test="$characteristicType='1'">
                                <xsl:value-of select="fcs:qualityDescription"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:choose>
                                    <xsl:when test="fcs:rangeSet != ''">
                                        <xsl:value-of
                                                select="concat('от ', fcs:rangeSet/fcs:valueRange/fcs:min, ' до ', fcs:rangeSet/fcs:valueRange/fcs:max)"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="fcs:valueSet/fcs:concreteValue"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:otherwise>
                        </xsl:choose>
                    </td>
                    <td>
                        <xsl:value-of select="fcs:OKEI/fcs:name"/>
                    </td>
                </tr>
            </xsl:for-each>
        </xsl:if>
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
                    закупок для заказчика
                </xsl:when>
                <xsl:when test="$value = 'R'">реорганизацией заказчика или уполномоченного органа, осуществляющего размещение</xsl:when>
                <xsl:when test="$value = 'F'">изменением организации, осуществляющей размещение в соответствии со
                    статьей 26 Федерального закона № 44-ФЗ
                </xsl:when>
                <xsl:otherwise>Информация отсутствует</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <p align="center">
            <xsl:value-of select="$param1"/>
            <xsl:value-of select="$role"/>
            <xsl:value-of select="$param2"/>
        </p>
    </xsl:template>
</xsl:stylesheet>