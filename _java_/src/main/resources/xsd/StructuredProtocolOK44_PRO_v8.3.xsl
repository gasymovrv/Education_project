<?xml version="1.0" encoding="UTF-8"?><xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://zakupki.gov.ru/oos/printform/1" xmlns:oos="http://zakupki.gov.ru/oos/types/1" xmlns:common="http://zakupki.gov.ru/oos/common/1" xmlns:base="http://zakupki.gov.ru/oos/base/1" version="2.0" exclude-result-prefixes="ns0 oos">

    <xsl:output version="4.01" method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
    <xsl:template match="ns0:fcsProtocolOK2">
        <html>
            <head>
                <title>Печатная форма</title>
                <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
                <style type="text/css">
                    p{
                    text-align:justify;
                    }
                    p.subtitle{
                    text-align:center;
                    }
                    p.title{
                    text-align:center;
                    font-size:20px;
                    padding-top:20px;
                    font-weight:bold;
                    }
                    p.titlebottom{
                    font-style:italic;
                    text-align:center;
                    padding-bottom:20px;
                    }
                    .table{
                    border-collapse:collapse;
                    width:100%;
                    }
                    table{
                    width:auto;
                    }
                    .table td, .table th{
                    padding:5px 5px;
                    border-style:solid;
                    border-width:1px;
                    border-color:black;
                    }
                    #invis{
                    padding:5px 5px;
                    border-style:dotted;
                    border-width:0px;
                    border-color:white;
                    }
                    td{
                    padding:0px 5px 0px 5px;
                    }
                    td.header{
                    padding:0px 5px 0px 0px;
                    }
                    .underline{
                    text-align:center;
                    border-bottom:1px solid black;
                    font-style:italic;
                    }
                    .underlineTitle{
                    text-align:center;
                    font-size:12px;
                    vertical-align:top;
                    }
                    .linedData {
                    text-align: center;
                    vertical-align: bottom;
                    border-bottom: solid 1px black;
                    }
                    .underlinedData{
                    text-align: center;
                    font-size:12px;
                    vertical-align:top;
                    }
                    .PFheader{
                    white-space: nowrap;
                    text-align: left;
                    margin: 0px;
                    padding: 0px 5px 0px 0px;
                    width: 0px;
                    }
                    .newPage{
                    page-break-before: always;
                    }
                    .centered{
                    text-align: center;
                    }
                    .left-aligned{
                    text-align: left;
                    }
                    td.up{
                    vertical-align:top;
                    }
                    .PFplaceholder {
                    position: relative;
                    top:14px;
                    border-top:1px black solid;
                    line-height:40px;
                    font-size: 12px;
                    }
                </style>
            </head>
            <body>
                <xsl:call-template name="header">
                    <xsl:with-param name="title">Протокол рассмотрения и оценки заявок на участие в открытом конкурсе
                    </xsl:with-param>
                    <xsl:with-param name="modification_title">Протокол рассмотрения и оценки заявок на участие в
                        открытом конкурсе
                    </xsl:with-param>
                </xsl:call-template>

                <table style="padding:0px 20px; width:100%;">
                    <tr>
                        <th style="width:50%;"/>
                        <th style="width:25%;"/>
                        <th style="width:25%;"/>
                    </tr>
                    <tr>
                        <td class="linedData">
                            <xsl:value-of select="oos:place"/>
                        </td>
                        <td/>
                        <td class="linedData">
                            <xsl:call-template name="dateWithoutTimeSpelledMonth">
                                <xsl:with-param name="dateTime" select="oos:signDate"/>
                            </xsl:call-template>
                        </td>
                    </tr>
                    <tr>
                        <td class="underlinedData">
                            <p class="underlineTitle">(место рассмотрения и оценки заявок)</p>
                        </td>
                        <td/>
                        <td class="underlinedData">
                            <p class="underlineTitle">(дата подписания протокола)</p>
                        </td>
                    </tr>
                </table>

                <xsl:variable name="placingWayCode" select="oos:purchaseInfo/oos:placingWay/oos:code"/>

                <!--Реквизиты исправления протокола-->
                <!--Требования к разработке данного варианта использования должны быть включены в рамках доработки FCSNF-316-->

                <xsl:call-template name="modificationDefault"/>

                <h3>1. Повестка дня</h3>
                <!-- Информационная строка 1 -->
                <p>Повесткой дня является рассмотрение и оценка заявок на участие в открытом конкурсе в
                    порядке, установленном Федеральным законом от 05.04.2013 № 44-ФЗ «О контрактной системе
                    в сфере закупок товаров, работ, услуг для обеспечения государственных и муниципальных нужд» (далее
                    Федеральный закон № 44-ФЗ).
                </p>
                <!-- Информационная строка 2 -->
                <p>
                    <xsl:text>Вскрытие конвертов с заявками на участие в открытом конкурсе и открытие доступа
                        к поданным в форме электронных документов заявкам на участие в открытом конкурсе было проведено </xsl:text>
                    <xsl:choose>
                        <xsl:when test="count(oos:foundationProtocol/oos:date)&gt;0">
                            <xsl:call-template name="dateWithoutTimeSpelledMonth">
                                <xsl:with-param name="dateTime" select="oos:foundationProtocol/oos:date"/>
                                <xsl:with-param name="format" select="'datetime'"/>
                            </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                            <span class="PFplaceholder">(дата и время вскрытия конвертов, открытия доступа к электронным
                                документам заявок)
                            </span>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:text> по адресу </xsl:text>
                    <xsl:choose>
                        <xsl:when test="count(oos:foundationProtocol/oos:place)&gt;0">
                            <xsl:value-of select="oos:foundationProtocol/oos:place"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <span class="PFplaceholder">(место вскрытия конвертов, открытия доступа к электронным
                                документам заявок участников)
                            </span>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:text>.</xsl:text>
                </p>
                <!-- Информационная строка 2.1 -->
                <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                    <xsl:sort select="oos:lotNumber"/>
                    <xsl:if test="count(oos:lotInfo/oos:purchaseProlongation/oos:publishDate)&gt;0">
                        <p>
                            <xsl:text>В соответствии с частью 1 статьи 53 Федерального закона №44-ФЗ был продлен
                                срок рассмотрения и оценки заявок на участие в конкурсе на поставку товара, выполнение работы либо оказание услуги</xsl:text>
                            <xsl:if test="oos:lotInfo/text()">
                                <xsl:text>для лота №</xsl:text>
                                <xsl:value-of select="oos:lotNumber"/>
                            </xsl:if>
                            <xsl:text> и размещено уведомление о продлении срока рассмотрения и оценки заявок на официальном сайте www.zakupki.gov.ru  (</xsl:text>
                            <!--наименование опубликованного уведомления о продлении срока  в соответствии с разделом 3.6.2 ТЗ «ПРИЗ_ВИ_0000 Размещение информации закупки»-->
                            <!--!!!-->
                            <xsl:text>Уведомление о продлении срока рассмотрения и оценки заявок на участие в конкурсе от </xsl:text>
                            <xsl:call-template name="dateWithoutTime">
                                <xsl:with-param name="dateTime" select="oos:lotInfo/oos:purchaseProlongation/oos:publishDate"/>
                            </xsl:call-template>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="oos:lotInfo/oos:purchaseProlongation/oos:prolongationNumber"/>
                            <xsl:text>)</xsl:text>
                        </p>
                    </xsl:if>
                </xsl:for-each>
                <!-- Информационная строка 3 -->
                <p>
                    <xsl:text>Рассмотрение и оценка заявок на участие в открытом конкурсе были проведены в
                        срок с даты вскрытия конвертов с заявками и (или) открытия доступа к поданным в форме
                        электронных документов заявкам на участие в открытом конкурсе </xsl:text>
                    <xsl:choose>
                        <xsl:when test="count(oos:protocolDate)&gt;0">
                            <xsl:call-template name="printDefault">
                                <xsl:with-param name="value" select="oos:protocolDate"/>
                                <xsl:with-param name="dateType" select="'datetime'"/>
                            </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                            <span class="PFplaceholder">(дата и время проведения рассмотрения и оценки заявок)</span>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:text> по адресу </xsl:text>
                    <xsl:choose>
                        <xsl:when test="count(oos:place)&gt;0">
                            <xsl:value-of select="oos:place"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <span class="PFplaceholder">(место проведения рассмотрения и оценки заявок)</span>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:text>.</xsl:text>
                </p>

                <xsl:variable name="commission" select="oos:commission/oos:commissionMembers/oos:commissionMember"/>
                <xsl:variable name="isMultilot">
                    <xsl:choose>
                        <xsl:when test="count(oos:protocolLots/oos:protocolLot)&gt;1">
                            <xsl:value-of select="'true'"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="'false'"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>

                <h3>2. Существенные условия контракта</h3>
                <br/>
                <!-- Наименование объекта закупки:  -->
                <xsl:call-template name="UnderlinedInfoRecord1">
                    <xsl:with-param name="header" select="'Номер и наименование объекта закупки: '"/>
                    <xsl:with-param name="value" select="concat('Закупка №', oos:purchaseNumber, ' «',oos:purchaseInfo/oos:purchaseObjectInfo, '»')"/>
                </xsl:call-template>
                <xsl:if test="empty(../oos:isBudgetUnionState) or ../oos:isBudgetUnionState!='true'">
                    <xsl:variable name="ikzValue">
                        <xsl:for-each select="oos:purchaseInfo/oos:lots/oos:lot">
                            <xsl:for-each select="oos:customerRequirements/oos:customerRequirement">
                                <xsl:value-of select="concat(oos:purchaseCode, '&#10;')"/>
                            </xsl:for-each>
                        </xsl:for-each>
                    </xsl:variable>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header" select="'Идентификационный код закупки: '"/>
                        <xsl:with-param name="value" select="$ikzValue"/>
                    </xsl:call-template>
                </xsl:if>

                <xsl:variable name="lotcount" select="count(oos:protocolLots/oos:protocolLot)"/>
                <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                    <xsl:sort select="oos:lotNumber"/>
                    <xsl:if test="$lotcount&gt;1">
                        <xsl:call-template name="LotInfo"/>
                    </xsl:if>
                    <!-- Начальная (максимальная) цена контракта: -->
                    <xsl:variable name="maxPrice">
                        <xsl:call-template name="currency">
                            <xsl:with-param name="x" select="oos:lotInfo/oos:maxPrice"/>
                        </xsl:call-template>
                    </xsl:variable>
                    <xsl:variable name="spelledMaxPrice">
                        <xsl:if test="oos:lotInfo/oos:spelledMaxPrice!='' and oos:lotInfo/oos:currency/oos:code='RUB'">
                            <xsl:text> (</xsl:text>
                            <xsl:value-of select="oos:lotInfo/oos:spelledMaxPrice"/>
                            <xsl:text>)</xsl:text>
                        </xsl:if>
                    </xsl:variable>
                    <xsl:variable name="maxPriceValue">
                        <xsl:if test="$maxPrice/text()!=''">
                            <xsl:value-of select="$maxPrice"/>
                            <xsl:value-of select="concat(' ', oos:lotInfo/oos:currency/oos:name)"/>
                            <xsl:if test="$spelledMaxPrice/text()!=''">
                                <xsl:value-of select="$spelledMaxPrice"/>
                            </xsl:if>
                        </xsl:if>
                    </xsl:variable>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header" select="'Начальная (максимальная) цена контракта: '"/>
                        <xsl:with-param name="value" select="$maxPriceValue"/>
                        <xsl:with-param name="hint" select="'(начальная (максимальная) цена цифрами и прописью)'"/>
                    </xsl:call-template>
                    <xsl:variable name="maxPriceCurrency">
                        <xsl:if test="oos:lotInfo/oos:isMaxPriceCurrency!=''">
                            <xsl:call-template name="currency">
                                <xsl:with-param name="x" select="oos:lotInfo/oos:isMaxPriceCurrency/common:maxPriceCurrency"/>
                            </xsl:call-template>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="oos:lotInfo/oos:isMaxPriceCurrency/common:currency/base:name"/>
                        </xsl:if>
                    </xsl:variable>
                    <xsl:if test="$maxPriceCurrency!=''">
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="'Начальная (максимальная) цена контракта в валюте контракта: '"/>
                            <xsl:with-param name="value" select="$maxPriceCurrency"/>
                            <xsl:with-param name="hint" select="'(начальная (максимальная) цена в валюте контракта цифрами)'"/>
                        </xsl:call-template>
                    </xsl:if>
                    <!-- Источник финансирования:  -->
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header" select="'Источник финансирования: '"/>
                        <xsl:with-param name="value" select="oos:lotInfo/oos:financeSource"/>
                        <xsl:with-param name="hint" select="'(источник финансирования)'"/>
                    </xsl:call-template>
                    <!-- Цена единицы товара, работы, услуги:  -->
                    <xsl:if test="count(oos:lotInfo/oos:unitPrice)&gt;0">
                        <xsl:variable name="unitPrice">
                            <xsl:call-template name="currency">
                                <xsl:with-param name="x" select="oos:lotInfo/oos:unitPrice"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:variable name="spelledUnitPrice">
                            <xsl:if test="oos:lotInfo/oos:spelledUnitPrice!='' and oos:lotInfo/oos:currency/oos:code='RUB'">
                                <xsl:text> (</xsl:text>
                                <xsl:value-of select="oos:lotInfo/oos:spelledUnitPrice"/>
                                <xsl:text>)</xsl:text>
                            </xsl:if>
                        </xsl:variable>
                        <xsl:variable name="unitPriceValue">
                            <xsl:if test="$unitPrice/text()!=''">
                                <xsl:value-of select="$unitPrice"/>
                                <xsl:value-of select="concat(' ', oos:lotInfo/oos:currency/oos:name)"/>
                                <xsl:if test="$spelledUnitPrice/text()!=''">
                                    <xsl:value-of select="$spelledUnitPrice"/>
                                </xsl:if>
                            </xsl:if>
                        </xsl:variable>
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="'Цена единицы товара, работы, услуги: '"/>
                            <xsl:with-param name="value" select="$unitPriceValue"/>
                            <xsl:with-param name="hint" select="'(цена единицы товара, работы, услуги прописью)'"/>
                        </xsl:call-template>
                    </xsl:if>
                    <!-- Место доставки товара, выполнения работы или оказания услуги: -->
                    <xsl:if test="count(oos:lotInfo/oos:customers/oos:customer)=1">
                        <xsl:variable name="delivery">
                            <xsl:call-template name="DeliveryPlaceBlock">
                                <xsl:with-param name="deliveryPlace" select="oos:lotInfo/oos:deliveryPlace"/>
                                <xsl:with-param name="kladrPlaces" select="oos:lotInfo/oos:kladrPlaces"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="'Место доставки товара, выполнения работы или оказания услуги: '"/>
                            <xsl:with-param name="value" select="$delivery"/>
                            <xsl:with-param name="hint" select="'(место доставки товара, выполнения работы или оказания услуги)'"/>
                        </xsl:call-template>
                        <!-- Сроки поставки товара или завершения работы либо график оказания услуг: -->
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="'Сроки поставки товара или завершения работы либо график оказания услуг: '"/>
                            <xsl:with-param name="value" select="oos:lotInfo/oos:deliveryTerm"/>
                            <xsl:with-param name="hint" select="'(сроки поставки товара или завершения работы либо график оказания услуг)'"/>
                        </xsl:call-template>
                    </xsl:if>
                    <p>Преимущества, предоставляемые заказчиком:</p>
                    <xsl:choose>
                        <xsl:when test="count(oos:lotInfo/oos:preferenses/oos:preferense)&gt;0">
                            <xsl:for-each select="oos:lotInfo/oos:preferenses/oos:preferense">
                                <xsl:variable name="preference">
                                    <xsl:if test="oos:name/text()">
                                        <xsl:value-of select="oos:name"/>
                                        <xsl:if test="oos:prefValue/text()">
                                            <xsl:text> в размере </xsl:text>
                                            <xsl:value-of select="oos:prefValue"/>
                                            <xsl:text>%</xsl:text>
                                        </xsl:if>
                                        <xsl:choose>
                                            <xsl:when test="position()!=last()">;
                                                <br/>
                                            </xsl:when>
                                            <xsl:otherwise>.</xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:if>
                                </xsl:variable>
                                <xsl:call-template name="printDefault">
                                    <xsl:with-param name="value" select="$preference"/>
                                </xsl:call-template>
                                <br/>
                            </xsl:for-each>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:call-template name="printDefault">
                                <xsl:with-param name="value" select="'не установлены'"/>
                            </xsl:call-template>
                            <br/>
                        </xsl:otherwise>
                    </xsl:choose>
                    <p>Требования, предъявляемые к участникам закупки:</p>
                    <xsl:choose>
                        <xsl:when test="count(oos:lotInfo/oos:requirements/oos:requirement)&gt;0">
                            <xsl:for-each select="oos:lotInfo/oos:requirements/oos:requirement">
                                <xsl:variable name="requirement">
                                    <xsl:value-of select="oos:name"/>
                                    <xsl:if test="oos:name/text()">
                                        <xsl:choose>
                                            <xsl:when test="position()!=last()">;
                                                <br/>
                                            </xsl:when>
                                            <xsl:otherwise>.</xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:if>
                                </xsl:variable>
                                <xsl:call-template name="printDefault">
                                    <xsl:with-param name="value" select="$requirement"/>
                                </xsl:call-template>
                                <br/>
                            </xsl:for-each>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:call-template name="printDefault">
                                <xsl:with-param name="value" select="'не установлены'"/>
                            </xsl:call-template>
                            <br/>
                        </xsl:otherwise>
                    </xsl:choose>

                    <!-- Информационная строка 4 -->
                    <xsl:if test="count(oos:contractMulti)&gt;0">
                        <xsl:choose>
                            <xsl:when test="count(oos:contractMulti/oos:part10St34Case/oos:contractCount)&gt;0">
                                <p>
                                    <xsl:text>
                                        В конкурсной документации установлено право заключения контрактов с несколькими участниками открытого конкурса
                                        в соответствии с частью 10 статьи 34 Федерального закона 44-ФЗ в отношении одного предмета и с одними и теми же
                                        условиями контракта. Количество контрактов </xsl:text>
                                    <!--количество контрактов-->
                                    <xsl:value-of select="oos:contractMulti/oos:part10St34Case/oos:contractCount"/>
                                    <xsl:text>.</xsl:text>
                                </p>
                            </xsl:when>
                            <xsl:otherwise>
                                <p>
                                    <xsl:text>В конкурсной документации установлено право заключения контрактов
                                        с несколькими участниками на основании: </xsl:text>
                                    <xsl:value-of select="oos:contractMulti/oos:otherCases/oos:contractMultiJustification"/>
                                    <xsl:text>.</xsl:text>
                                </p>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:if>
                </xsl:for-each>

                <xsl:variable name="responsibleRole" select="oos:purchaseInfo/oos:purchaseResponsible/oos:responsibleRole"/>
                <xsl:variable name="spelledResponsibleRole">
                    <xsl:choose>
                        <xsl:when test="$responsibleRole = 'CU'">Заказчик</xsl:when>
                        <xsl:when test="$responsibleRole = 'OCU'">Заказчик в качестве Организатора совместного
                            конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'RA'">Уполномоченный орган</xsl:when>
                        <xsl:when test="$responsibleRole = 'ORA'">Уполномоченный орган в качестве Организатора
                            совместного конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'AI'">Уполномоченное учреждение</xsl:when>
                        <xsl:when test="$responsibleRole = 'OAI'">Уполномоченное учреждение в качестве Организатора
                            совместного конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'OA'">Организация, осуществляющая полномочия заказчика на
                            осуществление закупок на основании договора (соглашения) в соответствии с частью 6 статьи 15
                            Федерального закона № 44-ФЗ
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'OOA'">Организация, осуществляющая полномочия заказчика на
                            осуществление закупок на основании договора (соглашения) в соответствии с частью 6 статьи 15
                            Федерального закона № 44-ФЗ, в качестве Организатора совместного конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'CS'">Заказчик, осуществляющий закупки в соответствии с
                            частью 5 статьи 15 Федерального закона № 44-ФЗ
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'OCS'">Заказчик, осуществляющий закупки в соответствии с
                            частью 5 статьи 15 Федерального закона № 44-ФЗ в качестве Организатора совместного конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'CC'">Заказчик, осуществляющий закупки в соответствии с
                            Федеральным законом № 44-ФЗ, в связи с неразмещением положения о закупке в соответствии с
                            положениями Федерального закона № 223-ФЗ
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'OCC'">Заказчик, осуществляющий закупки в соответствии с
                            Федеральным законом № 44-ФЗ, в связи с неразмещением положения о закупке в соответствии с
                            положениями Федерального закона № 223-ФЗ в качестве Организатора совместного конкурса
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'AU'">Заказчик, осуществляющий закупку на проведение
                            обязательного аудита
                        </xsl:when>
                        <xsl:when test="$responsibleRole = 'OAU'">Заказчик, осуществляющий закупку на проведение
                            обязательного аудита в качестве Организатора совместного конкурса
                        </xsl:when>

                    </xsl:choose>
                </xsl:variable>
                <!-- 3.	Информация о заказчике -->
                <br/>
                <h3>3. Информация о заказчике</h3>
                <xsl:variable name="organizationName" select="oos:purchaseInfo/oos:purchaseResponsible/oos:responsibleOrg/oos:fullName"/>
                <xsl:choose>
                    <xsl:when test="$responsibleRole='CU' or $responsibleRole='CS' or $responsibleRole='CC' or $responsibleRole='AU'">
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="hint" select="'(наименование заказчика)'"/>
                            <xsl:with-param name="value" select="$organizationName"/>
                        </xsl:call-template>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="concat('Определение поставщика осуществляет ',$spelledResponsibleRole, ': ')"/>
                            <xsl:with-param name="value" select="$organizationName"/>
                            <xsl:with-param name="hint" select="'(наименование организации, осуществляющей закупку)'"/>
                        </xsl:call-template>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:if test="($responsibleRole!='CU' and $responsibleRole!='CS' and $responsibleRole!='CC' and $responsibleRole!='AU') or $lotcount&gt;1">
                    <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                        <xsl:sort select="oos:lotNumber"/>
                        <xsl:if test="$lotcount&gt;1">
                            <xsl:call-template name="LotInfo"/>
                        </xsl:if>
                        <p>
                            <xsl:for-each select="oos:lotInfo/oos:customers/oos:customer">
                                <xsl:variable name="customer">
                                    <xsl:value-of select="oos:fullName"/>
                                    <xsl:if test="oos:fullName/text()">
                                        <xsl:choose>
                                            <xsl:when test="position()!=last()">;
                                                <br/>
                                            </xsl:when>
                                            <xsl:otherwise>.</xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:if>
                                </xsl:variable>
                                <xsl:call-template name="UnderlinedInfoRecord1">
                                    <xsl:with-param name="value" select="$customer"/>
                                    <xsl:with-param name="hint" select="'(наименование заказчика)'"/>
                                </xsl:call-template>
                            </xsl:for-each>
                        </p>
                    </xsl:for-each>
                </xsl:if>
                <!-- 4.	Информация о комиссии -->
                <br/>
                <h3>4. Информация о комиссии</h3>
                <xsl:call-template name="UnderlinedInfoRecord1">
                    <xsl:with-param name="header" select="'Комиссия: '"/>
                    <xsl:with-param name="value" select="oos:commission/oos:commissionName"/>
                    <xsl:with-param name="msg" select="'Наименование комиссии не задано'"/>
                </xsl:call-template>

                <p>На заседании комиссии по рассмотрению и оценке заявок на участие в открытом конкурсе
                    присутствовали:
                </p>
                <!-- Состав комиссии -->
                <xsl:choose>
                    <!--Если количество введенных и сохраненных членов комиссии = 0-->
                    <xsl:when test="count(oos:commission/oos:commissionMembers/oos:commissionMember)=0">
                        <xsl:text>Сведения о членах комиссии не заданы.</xsl:text>
                        <br/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:for-each select="oos:commission/oos:commissionMembers/oos:commissionMember">
                            <xsl:variable name="role">
                                <xsl:value-of select="oos:role/oos:name"/>
                                <xsl:text>: </xsl:text>
                            </xsl:variable>
                            <xsl:variable name="name">
                                <xsl:value-of select="oos:lastName"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="oos:firstName"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="oos:middleName"/>
                            </xsl:variable>

                            <xsl:call-template name="UnderlinedInfoRecord1">
                                <xsl:with-param name="header" select="$role"/>
                                <xsl:with-param name="value" select="$name"/>
                                <xsl:with-param name="hint" select="'(ФИО)'"/>
                            </xsl:call-template>
                        </xsl:for-each>

                        <!-- Информационная строка 5 -->
                        <xsl:variable name="presentMember">
                            <xsl:value-of select="count(oos:commission/oos:commissionMembers/oos:commissionMember)"/>
                            <xsl:text> (</xsl:text>
                            <xsl:value-of select="oos:commission/oos:commissionMembers/oos:spelledMembersCount"/>
                            <xsl:text>)</xsl:text>
                        </xsl:variable>
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="'Количество присутствовавших членов комиссии: '"/>
                            <xsl:with-param name="value" select="$presentMember"/>
                        </xsl:call-template>
                        <!-- Информационная строка 6 -->
                        <xsl:choose>
                            <xsl:when test="count(oos:commission/oos:commissionMembers/oos:commissionMember/oos:role[oos:rightVote='false'])&gt;0">
                                <xsl:call-template name="UnderlinedInfoRecord1">
                                    <xsl:with-param name="header" select="'из них не голосующих членов комиссии: '"/>
                                    <xsl:with-param name="value">
                                        <xsl:value-of select="count(oos:commission/oos:commissionMembers/oos:commissionMember/oos:role[oos:rightVote='false'])"/>
                                        <xsl:text> (</xsl:text>
                                        <xsl:value-of select="oos:commission/oos:commissionMembers/oos:spelledMembersNoVoteCount"/>
                                        <xsl:text>).</xsl:text>
                                    </xsl:with-param>
                                </xsl:call-template>
                            </xsl:when>
                            <xsl:otherwise>
                                <p>из них не голосующие члены комиссии отсутствуют.</p>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:otherwise>
                </xsl:choose>

                <!-- Информационная строка 7-->
                <!--если был установлен чек-бокс «Комиссия правомочна осуществлять свои функции в соответствии с Федеральным законом № 44-ФЗ»-->
                <xsl:if test="oos:commission/oos:competent=true()">
                    <p>Комиссия правомочна осуществлять свои функции в соответствии с частью 8 статьи 39 Федерального
                        закона от 05.04.2013 № 44-ФЗ «О контрактной системе в сфере закупок товаров, работ, услуг для
                        обеспечения государственных и муниципальных нужд», кворум имеется.
                    </p>
                </xsl:if>

                <!-- Информационная строка 8-->
                <xsl:if test="count(oos:commission/oos:addInfo)&gt;0">
                    <xsl:value-of select="oos:commission/oos:addInfo"/>
                    <br/>
                </xsl:if>
                <br/>
                <br/>

                <!-- 5.	Результаты рассмотрения и оценки заявок -->
                <h3>5. Результаты рассмотрения и оценки заявок</h3>
                <!-- Информационная строка 5 -->
                <xsl:choose>
                    <xsl:when test="count(oos:protocolLots/oos:protocolLot[count(oos:applications/oos:application)&gt;1])&gt;0">
                        <p>5.1 Комиссия рассмотрела заявки на участие в открытом конкурсе на предмет
                            соответствия требованиям, установленным в конкурсной документации, а также требованиям
                            Федерального закона № 44-ФЗ и приняла следующие решения:
                        </p>
                    </xsl:when>
                    <xsl:when test="count(oos:protocolLots/oos:protocolLot[count(oos:applications/oos:application)=1])&gt;0">
                        <p>5.1 Комиссия рассмотрела единственную заявку на участие в открытом конкурсе
                            на предмет соответствия требованиям, установленным в конкурсной документации, а
                            также требованиям Федерального закона № 44-ФЗ и приняла следующее решение:
                        </p>
                    </xsl:when>
                </xsl:choose>
                <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                    <xsl:sort select="oos:lotNumber"/>
                    <!-- Информационная строка 7 -->
                    <xsl:if test="$isMultilot='true'">
                        <xsl:call-template name="LotInfo"/>
                    </xsl:if>
                    <xsl:call-template name="ApplicationSubmission"/>
                    <!-- Информационная строка 8 -->
                    <xsl:call-template name="ApplicationCorrespondence"/>
                    <xsl:call-template name="ApplicationRejection"/>
                    <!-- Информационная строка 9 -->
                    <xsl:choose>
                        <!--Если в протоколе на вкладке «Список заявок по лотам» для конкретного лота установлен переключатель, отражающий причину признания закупки несостоявшейся-->
                        <xsl:when test="count(oos:abandonedReason)&gt;0">
                            <p>
                                <!--xsl:text>Открытый конкурс признан несостоявшимся.</xsl:text>
                                <br/-->
                                <!--если в протоколе установлен переключатель, отражающий причину признания закупки несостоявшейся с кодом типа = «???»-->
                                <xsl:choose>
                                    <xsl:when test="oos:abandonedReason/oos:type='OV'">По результатам рассмотрения
                                        заявок только одна заявка признана соответствующей требованиям, указанным в
                                        конкурсной документации. Открытый конкурс признан несостоявшимся по основанию,
                                        предусмотренному частью 6 статьи 53 Федерального закона № 44-ФЗ.
                                    </xsl:when>
                                    <xsl:when test="oos:abandonedReason/oos:type='NV'">По результатам рассмотрения
                                        заявок все заявки отклонены. Открытый конкурс признан несостоявшимся по
                                        основанию, предусмотренному частью 6 статьи 53 Федерального закона № 44-ФЗ.
                                    </xsl:when>
                                </xsl:choose>
                            </p>
                        </xsl:when>
                    </xsl:choose>
                    <xsl:if test="count(oos:abandonedReason)=0 or (count(oos:abandonedReason)&gt; 0 and oos:abandonedReason/oos:type!='NV')">
                        <!--Информация о результатах рассмотрения и оценки заявок-->
                        <p>Информация об участниках конкурса, заявки на участие в конкурсе которых были рассмотрены:</p>
                        <!-- Сортировка в таблице: Если указан номер заявки, сортировка должна выполняться по номеру заявки, а если номер отсутствует, то в порядке сохранения -->
                        <table class="table">
                            <tr>
                                <th>Номер заявки</th>
                                <th>Дата и время подачи заявки</th>
                                <th>Информация об участнике</th>
                                <th>Предлагаемая цена (стоимость),
                                    <br/>
                                    <xsl:choose>
                                        <xsl:when test="oos:lotInfo/oos:isMaxPriceCurrency!=''">
                                            <xsl:value-of select="oos:lotInfo/oos:isMaxPriceCurrency/common:currency/base:name"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="oos:lotInfo/oos:currency/oos:name"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </th>
                                <th>Результаты рассмотрения заявок</th>
                            </tr>
                            <xsl:for-each select="oos:applications/oos:application">
                                <!--сортирует все: числовые номера вперед, строчные номера назад-->
                                <xsl:sort select="not(number(oos:journalNumber))"/>
                                <!--сортирует только числовые номера по возрастанию-->
                                <xsl:sort select="number(oos:journalNumber)"/>
                                <!--сортирует все: есть дата вперед, нет даты назад-->
                                <xsl:sort select="not(oos:appDate/text())"/>
                                <!--сотирует буквенные номера по дате по возрастанию-->
                                <xsl:sort select="format-dateTime(                                     adjust-dateTime-to-timezone(oos:appDate),'[Y0001][M01][D01][H01][m01][s01]')" data-type="number"/>
                                <tr style="padding-top:10px;padding-bottom:10px">
                                    <td class="centered up">
                                        <xsl:choose>
                                            <xsl:when test="oos:journalNumber/text()">
                                                <xsl:value-of select="oos:journalNumber"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td class="centered up">
                                        <xsl:choose>
                                            <xsl:when test="oos:appDate/text()">
                                                <xsl:call-template name="dateWithTime">
                                                    <xsl:with-param name="dateTime" select="oos:appDate"/>
                                                </xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td class="up">
                                        <xsl:call-template name="ApplicationParticipantsInfo"/>
                                    </td>
                                    <td class="centered up">
                                        <!-- отображается <значение поля «Предлагаемая цена (стоимость)»> -->
                                        <xsl:choose>
                                            <xsl:when test="string(number(oos:offerPrice)) != 'NaN'">
                                                <xsl:call-template name="currency">
                                                    <xsl:with-param name="x" select="oos:offerPrice"/>
                                                </xsl:call-template>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:text>не указано</xsl:text>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td class="centered up">
                                        <xsl:choose>
                                            <xsl:when test="count(oos:admittedInfo/oos:appRejectedReason)&gt;0">
                                                <xsl:text>Отклонена</xsl:text>
                                            </xsl:when>
                                            <xsl:when test="count(oos:admittedInfo/oos:admitted)&gt;0">
                                                <xsl:text>Соответствует требованиям</xsl:text>
                                                <br/>
                                            </xsl:when>
                                            <xsl:when test="count(oos:admittedInfo/oos:appRating)&gt;0 or count(oos:admittedInfo/oos:score)&gt;0 or count(oos:admittedInfo/oos:conditionsScoring)&gt;0">
                                                <xsl:text>Соответствует требованиям</xsl:text>
                                                <br/>
                                                <xsl:variable name="totalPreference" select="oos:overallValue"/>
                                                <xsl:if test="number($totalPreference) &gt; 0">
                                                    <p>
                                                        <xsl:text>Преимущество в размере </xsl:text>
                                                        <xsl:value-of select="format-number(oos:overallValue, '#.##')"/>
                                                        <xsl:text>%</xsl:text>
                                                    </p>
                                                </xsl:if>
                                            </xsl:when>
                                        </xsl:choose>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:if>
                    <xsl:if test="oos:applications/oos:application[count(oos:admittedInfo/oos:appRejectedReason)&gt;0]">
                        <!-- Информация об участниках конкурса, заявки на участие в конкурсе которых были отклонены -->
                        <p>Информация об участниках конкурса, заявки на участие в конкурсе которых были отклонены:</p>
                        <table class="table">
                            <tr>
                                <th>Номер заявки</th>
                                <th>Наименование / ФИО участника</th>
                                <th>Решение каждого члена комиссии об отклонении заявок на участие в конкурсе</th>
                                <th>Причина и обоснование причины отклонения</th>
                            </tr>
                            <xsl:for-each select="oos:applications/oos:application[count(oos:admittedInfo/oos:appRejectedReason)&gt;0]">
                                <!--сортирует все: числовые номера вперед, строчные номера назад-->
                                <xsl:sort select="not(number(oos:journalNumber))"/>
                                <!--сортирует только числовые номера по возрастанию-->
                                <xsl:sort select="number(oos:journalNumber)"/>
                                <!--сортирует все: есть дата вперед, нет даты назад-->
                                <xsl:sort select="not(oos:appDate/text())"/>
                                <!--сотирует буквенные номера по дате по возрастанию-->
                                <xsl:sort select="format-dateTime(                                     adjust-dateTime-to-timezone(oos:appDate),'[Y0001][M01][D01][H01][m01][s01]')" data-type="number"/>

                                <tr style="padding-top:10px;padding-bottom:10px">
                                    <td class="centered up">
                                        <xsl:choose>
                                            <xsl:when test="oos:journalNumber/text()">
                                                <xsl:value-of select="oos:journalNumber"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td class="up">
                                        <xsl:for-each select="oos:appParticipants/oos:appParticipant">
                                            <xsl:call-template name="ApplicationParticipantShortInfo"/>
                                            <br/>
                                        </xsl:for-each>
                                    </td>
                                    <td class="up">
                                        <xsl:variable name="admittedCount" select="count(oos:admissionResults/oos:admissionResult[oos:admitted='true'])"/>
                                        <xsl:variable name="rejectedCount" select="count(oos:admissionResults/oos:admissionResult[oos:admitted='false'])"/>
                                        <xsl:choose>
                                            <xsl:when test="$rejectedCount&gt;0 or $admittedCount&gt;0">
                                                <xsl:if test="$rejectedCount&gt;0">
                                                    <p>Заявка не соответствует требованиям и должна быть отклонена по
                                                        решению члена(ов) комиссии:
                                                    </p>
                                                    <xsl:for-each select="oos:admissionResults/oos:admissionResult[oos:admitted='false']">
                                                        <xsl:sort select="oos:memberNumber"/>
                                                        <xsl:variable name="memberNumber" select="oos:protocolCommissionMember/oos:memberNumber"/>
                                                        <xsl:variable name="FIO" select="$commission[oos:memberNumber = $memberNumber]"/>
                                                        <xsl:if test="count($FIO)=1">
                                                            <p>
                                                                <xsl:value-of select="$FIO/oos:lastName"/>
                                                                <xsl:text> </xsl:text>
                                                                <xsl:value-of select="$FIO/oos:firstName"/>
                                                                <xsl:text> </xsl:text>
                                                                <xsl:value-of select="$FIO/oos:middleName"/>
                                                            </p>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:if>
                                                <xsl:if test="$admittedCount&gt;0">
                                                    <!--  Часть «Заявка соответствует требованиям по решению члена(ов) комиссии;» - не отображается если таких членов комиссии нет.-->
                                                    <p>Заявка соответствует требованиям по решению члена(ов) комиссии:
                                                    </p>
                                                    <xsl:for-each select="oos:admissionResults/oos:admissionResult[oos:admitted='true']">
                                                        <xsl:sort select="oos:memberNumber"/>
                                                        <xsl:variable name="memberNumber" select="oos:protocolCommissionMember/oos:memberNumber"/>
                                                        <xsl:variable name="FIO" select="$commission[oos:memberNumber = $memberNumber]"/>
                                                        <xsl:if test="count($FIO)=1">
                                                            <p>
                                                                <xsl:value-of select="$FIO/oos:lastName"/>
                                                                <xsl:text> </xsl:text>
                                                                <xsl:value-of select="$FIO/oos:firstName"/>
                                                                <xsl:text> </xsl:text>
                                                                <xsl:value-of select="$FIO/oos:middleName"/>
                                                            </p>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:if>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <p>
                                                    <xsl:text>-</xsl:text>
                                                </p>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                    <td class="up">
                                        <xsl:choose>
                                            <xsl:when test="count(oos:admittedInfo/oos:appRejectedReason)&gt;0">
                                                <xsl:for-each select="oos:admittedInfo/oos:appRejectedReason">
                                                    <xsl:if test="oos:nsiRejectReason/oos:reason/text()">
                                                        <xsl:call-template name="printDefault">
                                                            <xsl:with-param name="value" select="oos:nsiRejectReason/oos:reason"/>
                                                        </xsl:call-template>
                                                        <br/>
                                                    </xsl:if>
                                                    <xsl:call-template name="printDefault">
                                                        <xsl:with-param name="value" select="oos:explanation"/>
                                                    </xsl:call-template>
                                                    <br/>
                                                </xsl:for-each>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <!-- Причина и обоснование причины отклонения -->
                                                <xsl:for-each select="oos:admissionResults/oos:admissionResult/oos:appRejectedReason">
                                                    <xsl:if test="oos:nsiRejectReason/oos:reason/text()">
                                                        <xsl:call-template name="printDefault">
                                                            <xsl:with-param name="value" select="oos:nsiRejectReason/oos:reason"/>
                                                        </xsl:call-template>
                                                        <xsl:text>: </xsl:text>
                                                        <br/>
                                                    </xsl:if>
                                                    <xsl:call-template name="printDefault">
                                                        <xsl:with-param name="value" select="oos:explanation"/>
                                                    </xsl:call-template>
                                                    <br/>
                                                </xsl:for-each>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:if>
                </xsl:for-each>

                <xsl:if test="count(oos:protocolLots/oos:protocolLot/oos:abandonedReason)=0 and count(oos:protocolLots/oos:protocolLot/oos:applications/oos:application[count(oos:admittedInfo/oos:appRating)&gt;0])&gt;1">
                    <p>5.2. Комиссия осуществила оценку заявок на участие в открытом конкурсе для выявления победителя
                        конкурса на основе критериев, указанных в конкурсной документации и получила следующие
                        результаты:
                    </p>
                    <br/>
                    <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                        <xsl:sort select="oos:lotNumber"/>
                        <xsl:variable name="currency">
                            <xsl:choose>
                                <xsl:when test="oos:lotInfo/oos:isMaxPriceCurrency!=''">
                                    <xsl:value-of select="oos:lotInfo/oos:isMaxPriceCurrency/common:currency/base:name"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="oos:lotInfo/oos:currency/oos:name"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:variable>
                        <xsl:variable name="currencyCode">
                            <xsl:choose>
                                <xsl:when test="oos:lotInfo/oos:isMaxPriceCurrency!=''">
                                    <xsl:value-of select="oos:lotInfo/oos:isMaxPriceCurrency/common:currency/base:code"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="oos:lotInfo/oos:currency/oos:code"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:variable>
                        <!-- если допущено более одной заявки -->
                        <!-- если конкурс признан состоявщимся -->
                        <xsl:if test="count(oos:abandonedReason)=0 and count(oos:applications/oos:application[count(oos:admittedInfo/oos:appRating)&gt;0])&gt;1">
                            <xsl:if test="$isMultilot='true'">
                                <xsl:call-template name="LotInfo"/>
                            </xsl:if>
                            <p>
                                <xsl:text>- присвоенные заявкам на участие в конкурсе значения по каждому из критериев оценки заявок на участие в конкурсе (Приложение 1);</xsl:text>
                                <br/>
                                <xsl:text>- принятое на основании результатов оценки заявок на участие в открытом конкурсе решение о присвоении таким заявкам порядковых номеров (Приложение 1);</xsl:text>
                            </p>
                            <xsl:for-each select="oos:applications/oos:application[oos:admittedInfo/oos:appRating&lt;3]">
                                <xsl:sort select="oos:admittedInfo/oos:appRating"/>
                                <!--сортирует все: числовые номера вперед, строчные номера назад-->
                                <xsl:sort select="not(number(oos:journalNumber))"/>
                                <!--сортирует только числовые номера по возрастанию-->
                                <xsl:sort select="number(oos:journalNumber)"/>
                                <!--сортирует все: есть дата вперед, нет даты назад-->
                                <xsl:sort select="not(oos:appDate/text())"/>
                                <!--сотирует буквенные номера по дате по возрастанию-->
                                <xsl:sort select="format-dateTime(                                     adjust-dateTime-to-timezone(oos:appDate),'[Y0001][M01][D01][H01][m01][s01]')" data-type="number"/>

                                <xsl:choose>
                                    <xsl:when test="oos:admittedInfo/oos:appRating = 1">
                                        <p>
                                            <b>
                                                <xsl:text>Победителем признан участник с номером заявки №</xsl:text>
                                                <xsl:call-template name="printDefault">
                                                    <xsl:with-param name="value" select="oos:journalNumber"/>
                                                </xsl:call-template>
                                                <xsl:text>, получившей первый номер: </xsl:text>
                                            </b>
                                            <br/>
                                            <xsl:for-each select="oos:appParticipants/oos:appParticipant">
                                                <xsl:call-template name="ApplicationParticipantShortInfo"/>
                                                <br/>
                                                <xsl:value-of select="concat('Почтовый адрес: ', oos:postAddress)"/>
                                                <br/>
                                            </xsl:for-each>
                                        </p>
                                    </xsl:when>
                                    <xsl:when test="oos:admittedInfo/oos:appRating = 2">
                                        <p>
                                            <b>Второй номер</b>
                                            <xsl:text> присвоен участнику с номером заявки №</xsl:text>
                                            <xsl:call-template name="printDefault">
                                                <xsl:with-param name="value" select="oos:journalNumber"/>
                                            </xsl:call-template>
                                            <xsl:text>: </xsl:text>
                                            <br/>
                                            <xsl:for-each select="oos:appParticipants/oos:appParticipant">
                                                <xsl:call-template name="ApplicationParticipantShortInfo"/>
                                                <br/>
                                                <xsl:value-of select="concat('Почтовый адрес: ', oos:postAddress)"/>
                                                <br/>
                                            </xsl:for-each>
                                        </p>
                                    </xsl:when>
                                </xsl:choose>

                                <xsl:variable name="offerPrice">
                                    <xsl:call-template name="currency">
                                        <xsl:with-param name="x" select="oos:offerPrice"/>
                                    </xsl:call-template>
                                    <xsl:value-of select="concat(' ', $currency)"/>
                                </xsl:variable>
                                <xsl:variable name="spelledOfferPrice">
                                    <xsl:if test="oos:spelledOfferPrice!='' and $currencyCode='RUB'">
                                        <xsl:text> (</xsl:text>
                                        <xsl:value-of select="oos:spelledOfferPrice"/>
                                        <xsl:text>)</xsl:text>
                                    </xsl:if>
                                </xsl:variable>

                                <xsl:if test="count(oos:offerPrice)&gt;0">
                                    <p>
                                        <xsl:text>предложение о цене контракта:  </xsl:text>
                                        <xsl:value-of select="concat($offerPrice, ' ', $spelledOfferPrice)"/>
                                    </p>
                                </xsl:if>

                                <xsl:variable name="totalPreference" select="oos:overallValue"/>
                                <xsl:if test="number($totalPreference) &gt; 0">
                                    <p>
                                        <xsl:text>(преимущество в размере </xsl:text>
                                        <xsl:value-of select="$totalPreference"/>
                                        <xsl:text>%)</xsl:text>
                                    </p>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:if>
                        <br/>
                    </xsl:for-each>
                </xsl:if>

                <!-- 6.	Результаты конкурса -->
                <h3>6. Результаты конкурса</h3>
                <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                    <xsl:sort select="oos:lotNumber"/>
                    <p>
                        <xsl:if test="$isMultilot='true'">
                            <xsl:call-template name="LotInfo"/>
                        </xsl:if>
                        <br/>
                        <xsl:choose>
                            <xsl:when test="oos:abandonedReason/oos:type='NV' and $placingWayCode!='PK44' and $placingWayCode!='POKU44'">
                                <xsl:text>В соответствии с частью 2 статьи 55 Федерального закона № 44-ФЗ в связи с тем, что по результатам рассмотрения заявок на участие в конкурсе конкурсная комиссия отклонила все заявки, заказчику необходимо внести изменения в план-график (при необходимости также в план закупок) и осуществить проведение повторного конкурса в соответствии с частью 3 статьи 55 Федерального закона № 44-ФЗ или осуществить новую закупку.</xsl:text>
                                <br/>
                            </xsl:when>
                            <xsl:when test="oos:abandonedReason/oos:type='NV' and ($placingWayCode='PK44' or $placingWayCode='POKU44')">
                                <xsl:text>В соответствии с частью 4 статьи 55 Федерального закона № 44-ФЗ в связи с тем, что по результатам рассмотрения заявок на участие в конкурсе конкурсная комиссия отклонила все заявки, заказчику необходимо осуществить данную закупку путем проведения запроса предложений в соответствии с пунктом 8 части 2 статьи 83 Федерального закона №44-ФЗ (при этом объект закупки не может быть изменен) или иным образом в соответствии с Федеральным законом №44-ФЗ</xsl:text>
                                <br/>
                            </xsl:when>
                            <xsl:when test="oos:abandonedReason/oos:type='OV'">
                                <xsl:text>По результатам конкурса должен быть заключен контракт с единственным поставщиком (подрядчиком, исполнителем) в соответствии с пунктом 25 части 1 статьи 93 Федерального закона № 44 ФЗ.</xsl:text>
                            </xsl:when>
                            <xsl:when test="oos:abandonedReason/oos:type='OR'">
                                <xsl:text>По результатам конкурса должен быть заключен контракт с единственным поставщиком (подрядчиком, исполнителем) в соответствии с пунктом 25 части 1 статьи 93 Федерального закона № 44 ФЗ.</xsl:text>
                            </xsl:when>
                            <xsl:when test="count(oos:abandonedReason)=0 and count(oos:applications/oos:application)&gt;1">
                                <xsl:text>По результатам конкурса должен быть заключен контракт на условиях, указанных в заявке на участие в конкурсе, поданной участником конкурса, с которым заключается контракт, и в конкурсной документации. Заключение контракта по результатам конкурса должно производиться в порядке и в сроки, указанные в статье 54 Федерального закона № 44-ФЗ.</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>Информация отсутствует</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </p>
                </xsl:for-each>

                <br/>
                <h3>7. Публикация и хранение протокола</h3>
                <!-- Информационная строка 15 -->
                <p>Настоящий протокол подлежит размещению на официальном сайте www.zakupki.gov.ru в порядке и в сроки,
                    установленные Федеральным законом от 05.04.2013 № 44-ФЗ «О контрактной системе в сфере закупок
                    товаров, работ, услуг для обеспечения государственных и муниципальных нужд».
                </p>
                <br/>
                <br/>

                <h3>8. Приложения к Протоколу</h3>
                <!-- 8. Приложения к Протоколу -->
                <xsl:call-template name="Attachments"/>

                <!-- Подписи членов комиссии -->
                <xsl:call-template name="Signatures">
                    <xsl:with-param name="commission" select="oos:commission"/>
                </xsl:call-template>

                <br/>
                <br/>
                <xsl:call-template name="Appendix">
                    <xsl:with-param name="lotcount" select="$lotcount"/>
                </xsl:call-template>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="Attachments">
        <xsl:variable name="noAbandonedReason" select="count(oos:protocolLots/oos:protocolLot[count(oos:abandonedReason)=0])&gt;0"/>
        <xsl:choose>
            <xsl:when test="count(oos:attachments/oos:attachment)&gt;0 or $noAbandonedReason">
                <p>К протоколу прилагаются и являются его неотъемлемой частью:</p>
                <xsl:if test="$noAbandonedReason">
                    <p>1. Оценка предложений участников по критериям оценок на ____л.</p>
                </xsl:if>
                <xsl:variable name="increment">
                    <xsl:choose>
                        <xsl:when test="$noAbandonedReason">1</xsl:when>
                        <xsl:otherwise>0</xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>

                <xsl:if test="count(oos:attachments/oos:attachment)&gt;0">
                    <p>
                        <xsl:for-each select="oos:attachments/oos:attachment">
                            <xsl:value-of select="position()+$increment"/>
                            <xsl:text>. </xsl:text>
                            <xsl:value-of select="oos:docDescription/text()"/>
                            <xsl:text> (</xsl:text>
                            <xsl:value-of select="oos:fileName/text()"/>
                            <xsl:text>)</xsl:text>
                            <br/>
                        </xsl:for-each>
                    </p>
                </xsl:if>
            </xsl:when>
            <xsl:otherwise>
                <p>Приложения к протоколу отсутствуют.</p>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="LotInfo">
        <p>
            <xsl:text>Лот №</xsl:text>
            <xsl:call-template name="printDefault">
                <xsl:with-param name="value" select="oos:lotNumber"/>
            </xsl:call-template>
            <xsl:text> </xsl:text>
            <xsl:text>"</xsl:text>
            <xsl:call-template name="printDefault">
                <xsl:with-param name="value" select="oos:lotInfo/oos:lotObjectInfo"/>
            </xsl:call-template>
            <xsl:text>":</xsl:text>
        </p>
    </xsl:template>

    <xsl:template name="header">
        <xsl:param name="title" select="''"/>
        <xsl:param name="modification_title" select="''"/>
        <p class="title">
            <xsl:choose>
                <xsl:when test="oos:modification/text()!=''">
                    <xsl:value-of select="$modification_title"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$title"/>
                </xsl:otherwise>
            </xsl:choose>
        </p>
        <p class="subtitle">
            <xsl:if test="oos:signDate/text()">
                <xsl:text> от </xsl:text>
                <xsl:call-template name="dateWithoutTime">
                    <xsl:with-param name="dateTime" select="oos:signDate"/>
                </xsl:call-template>
            </xsl:if>
            <xsl:if test="oos:protocolNumber/text()">
                <xsl:text> №</xsl:text>
                <xsl:value-of select="oos:protocolNumber"/>
            </xsl:if>

            <xsl:if test="oos:purchaseNumber/text()!=''">
                <xsl:text> для закупки №</xsl:text>
                <xsl:value-of select="oos:purchaseNumber"/>
            </xsl:if>
        </p>
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
            <xsl:when test="string($value)!=''">
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

    <xsl:template name="headerCaption">
        <xsl:param name="caption"/>
        <tr>
            <td>
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
                                <xsl:variable name="vFactor" select="substring('100000000000000000000000000000000000000000000',                                     1, substring($vExponent,2) + 1)"/>
                                <xsl:value-of select="$vMantissa div $vFactor"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:variable name="vFactor" select="substring('100000000000000000000000000000000000000000000',                                     1, $vExponent + 1)"/>
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
    <xsl:template name="currency">
        <xsl:param name="x"/>
        <xsl:param name="format" select="'0.00'"/>
        <xsl:value-of select="format-number($x, $format)"/>
    </xsl:template>

    <xsl:template name="modificationDefault">
        <xsl:if test="count(oos:modification)!=0">
            <!--h3>Реквизиты исправления протокола</h3-->
            <xsl:variable name="changeType" select="oos:modification/oos:reason"/>
            <xsl:variable name="reason">
                <xsl:choose>
                    <xsl:when test="count($changeType/oos:responsibleDecision)!=0">
                        <xsl:variable name="date">
                            <xsl:call-template name="dateWithoutTime">
                                <xsl:with-param name="dateTime" select="$changeType/oos:responsibleDecision/oos:decisionDate"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:value-of select="concat('Решение Заказчика (организации, осуществляющей определение поставщика (подрядчика, исполнителя) для заказчика)', ' от ',$date)"/>
                    </xsl:when>
                    <xsl:when test="count($changeType/oos:authorityPrescription)!=0">
                        <xsl:choose>
                            <xsl:when test="count($changeType/oos:authorityPrescription/oos:reestrPrescription)!=0">
                                <!-- Наименование контролирующего органа -->
                                <xsl:variable name="authority" select="$changeType/oos:authorityPrescription/oos:reestrPrescription/oos:authorityName"/>
                                <!-- дата предписания -->
                                <xsl:variable name="date">
                                    <xsl:call-template name="dateWithoutTime">
                                        <xsl:with-param name="dateTime" select="$changeType/oos:authorityPrescription/oos:reestrPrescription/oos:docDate"/>
                                    </xsl:call-template>
                                </xsl:variable>
                                <xsl:value-of select="concat('Предписание контролирующего органа &quot;', $authority, '&quot; №', $changeType/oos:authorityPrescription/oos:reestrPrescription/oos:prescriptionNumber, ' от ', $date)"/>
                            </xsl:when>
                            <xsl:when test="count($changeType/oos:authorityPrescription/oos:externalPrescription)!=0">
                                <xsl:variable name="date">
                                    <xsl:call-template name="dateWithoutTime">
                                        <xsl:with-param name="dateTime" select="$changeType/oos:authorityPrescription/oos:externalPrescription/oos:docDate"/>
                                    </xsl:call-template>
                                </xsl:variable>
                                <xsl:value-of select="concat('Предписание органа, уполномоченного на осуществление контроля, ', $changeType/oos:authorityPrescription/oos:externalPrescription/oos:docName, ' от ', $date, ' №', $changeType/oos:authorityPrescription/oos:externalPrescription/oos:docNumber, ', Контролирующий орган &quot;', $changeType/oos:authorityPrescription/oos:externalPrescription/oos:authorityName, '&quot;')"/>
                            </xsl:when>
                        </xsl:choose>
                    </xsl:when>
                    <xsl:when test="count($changeType/oos:courtDecision)!=0">
                        <xsl:variable name="date">
                            <xsl:call-template name="dateWithoutTime">
                                <xsl:with-param name="dateTime" select="$changeType/oos:courtDecision/oos:docDate"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:value-of select="concat('Решение судебного органа, ', $changeType/oos:courtDecision/oos:docName, ' от ', $date, ' №', $changeType/oos:courtDecision/oos:docNumber, ', Судебный орган  &quot;', $changeType/oos:courtDecision/oos:courtName ,'&quot;')"/>
                    </xsl:when>
                    <xsl:when test="count($changeType/oos:discussionResult)!=0">
                        <xsl:variable name="date">
                            <xsl:call-template name="dateWithoutTime">
                                <xsl:with-param name="dateTime" select="$changeType/oos:discussionResult/oos:docDate"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:value-of select="concat('По результатам общественного обсуждения в соответствии с пунктом 4 части 3 статьи 112 Федерального закона №44-ФЗ, ', $changeType/oos:discussionResult/oos:docName, ' от ', $date,' №', $changeType/oos:discussionResult/oos:docNumber)"/>
                    </xsl:when>
                </xsl:choose>
            </xsl:variable>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="'Основание: '"/>
                <xsl:with-param name="value" select="$reason"/>
            </xsl:call-template>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="'Краткое описание изменения: '"/>
                <xsl:with-param name="value" select="oos:modification/oos:info"/>
                <xsl:with-param name="msg" select="'Информация отсутствует'"/>
            </xsl:call-template>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="'Дополнительная информация: '"/>
                <xsl:with-param name="value" select="oos:modification/oos:addInfo"/>
                <xsl:with-param name="msg" select="'Информация отсутствует'"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>

    <xsl:template name="ApplicationSubmission">
        <p>
            <xsl:text>По окончании срока подачи заявок на участие в открытом конкурсе подано заявок – </xsl:text>
            <xsl:variable name="totalApplicationCount" select="count(oos:applications/oos:application)"/>
            <xsl:choose>
                <xsl:when test="$totalApplicationCount&gt;0 and oos:lotInfo/oos:spelledAppCount/text()">
                    <u>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="string($totalApplicationCount)"/>
                        </xsl:call-template>
                        <xsl:text> (</xsl:text>
                        <!-- всего подано заявок ПРОПИСЬЮ -->
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="oos:lotInfo/oos:spelledAppCount"/>
                        </xsl:call-template>
                        <xsl:text>) шт.;</xsl:text>
                    </u>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>______________ (____________________________) шт.</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </p>
    </xsl:template>

    <xsl:template name="ApplicationCorrespondence">
        <p>
            <xsl:text>из них соответствуют требованиям - </xsl:text>
            <xsl:variable name="correspondAppsCount" select="count(oos:applications/oos:application[count(oos:admittedInfo/oos:appRating)&gt;0])"/>
            <xsl:variable name="correspondAppsCountScore" select="count(oos:applications/oos:application[count(oos:admittedInfo/oos:score)&gt;0])"/>
            <xsl:variable name="correspondAppsCountCondition" select="count(oos:applications/oos:application[count(oos:admittedInfo/oos:conditionsScoring)&gt;0])"/>
            <xsl:variable name="max_">
                <xsl:choose>
                    <xsl:when test="$correspondAppsCountCondition &gt; $correspondAppsCount">
                        <xsl:value-of select="$correspondAppsCountCondition"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$correspondAppsCount"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:variable name="max">
                <xsl:choose>
                    <xsl:when test="$max_ &gt; $correspondAppsCountScore">
                        <xsl:value-of select="$max_"/>
                    </xsl:when>
                    <xsl:when test="count(oos:applications/oos:application/oos:admittedInfo[oos:admitted]) &gt; $max_">
                        <xsl:value-of select="count(oos:applications/oos:application/oos:admittedInfo[oos:admitted])"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$correspondAppsCountScore"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:choose>
                <xsl:when test="$max&gt;0 or oos:lotInfo/oos:spelledValidAppCount/text()">
                    <u>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="string($max)"/>
                        </xsl:call-template>
                        <xsl:text> (</xsl:text>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="oos:lotInfo/oos:spelledValidAppCount"/>
                        </xsl:call-template>
                        <xsl:text>) шт.;</xsl:text>
                    </u>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>______________ (____________________________) шт.;</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </p>
    </xsl:template>

    <xsl:template name="ApplicationRejection">
        <p>
            <xsl:text>отклонено заявок - </xsl:text>
            <xsl:variable name="rejectedAppsCount" select="count(oos:applications/oos:application[count(oos:admittedInfo/oos:appRejectedReason)&gt;0])"/>
            <xsl:choose>
                <xsl:when test="$rejectedAppsCount &gt; 0 or oos:lotInfo/oos:spelledInvalidAppCount/text()">
                    <u>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="string($rejectedAppsCount)"/>
                        </xsl:call-template>
                        <xsl:text> (</xsl:text>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="oos:lotInfo/oos:spelledInvalidAppCount"/>
                        </xsl:call-template>
                        <xsl:text>) шт.</xsl:text>
                    </u>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>______________ (____________________________) шт.</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </p>
    </xsl:template>

    <xsl:template name="Appendix">
        <xsl:param name="lotcount"/>
        <xsl:if test="count(oos:protocolLots/oos:protocolLot[not(oos:abandonedReason)])&gt;0">
            <p style="text-align:right;">
                <xsl:text>Приложение № 1 к Протоколу рассмотрения </xsl:text>
                <br/>
                <xsl:text>и оценки заявок на участие в открытом </xsl:text>
                <br/>
                <xsl:text>конкурсе</xsl:text>
                <xsl:if test="count(oos:signDate)&gt;0">
                    <xsl:text> от </xsl:text>
                    <xsl:call-template name="printDefault">
                        <xsl:with-param name="value" select="oos:signDate"/>
                        <xsl:with-param name="dateType" select="'date'"/>
                        <xsl:with-param name="msg" select="'_____________'"/>
                    </xsl:call-template>
                </xsl:if>
                <xsl:if test="count(oos:protocolNumber)&gt;0">
                    <xsl:text> </xsl:text>
                    <xsl:if test="not(starts-with(oos:protocolNumber, '№'))">
                        <xsl:text>№</xsl:text>
                    </xsl:if>
                    <xsl:value-of select="oos:protocolNumber"/>
                </xsl:if>
            </p>
            <br/>
            <span style="text-align:center;">
                <h3>
                    <xsl:text>Оценка предложений участников по критериям оценок</xsl:text>
                </h3>
            </span>
            <br/>
            <xsl:for-each select="oos:protocolLots/oos:protocolLot">
                <xsl:if test="count(oos:abandonedReason)=0 or oos:abandonedReason/oos:type!='NV'">
                    <xsl:if test="$lotcount&gt;1">
                        <xsl:call-template name="LotInfo"/>
                    </xsl:if>
                    <xsl:call-template name="ApplicationSubmission"/>
                    <xsl:call-template name="ApplicationCorrespondence"/>

                    <table class="table" style="width:100%;">
                        <tr>
                            <th>Номер заявки</th>
                            <th>Информация об участнике</th>
                            <th>Условия исполнения контракта</th>
                            <th>Оценка заявки</th>
                            <th>Порядковый номер</th>
                        </tr>
                        <xsl:choose>
                            <xsl:when test="count(oos:applications/oos:application)=0">
                                <tr>
                                    <td/>
                                    <td/>
                                    <td/>
                                    <td/>
                                    <td/>
                                </tr>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:for-each select="oos:applications/oos:application">
                                    <!--сортирует все: числовые номера вперед, строчные номера назад-->
                                    <xsl:sort select="not(number(oos:journalNumber))"/>
                                    <!--сортирует только числовые номера по возрастанию-->
                                    <xsl:sort select="number(oos:journalNumber)"/>
                                    <!--сортирует все: есть дата вперед, нет даты назад-->
                                    <xsl:sort select="not(oos:appDate/text())"/>
                                    <!--сотирует буквенные номера по дате по возрастанию-->
                                    <xsl:sort select="format-dateTime(                                         adjust-dateTime-to-timezone(oos:appDate),'[Y0001][M01][D01][H01][m01][s01]')" data-type="number"/>

                                    <tr style="padding-top:10px;padding-bottom:10px">
                                        <td class="centered up">
                                            <xsl:choose>
                                                <xsl:when test="oos:journalNumber/text()">
                                                    <xsl:value-of select="oos:journalNumber"/>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </td>
                                        <td class="up">
                                            <xsl:for-each select="oos:appParticipants/oos:appParticipant">
                                                <xsl:call-template name="ApplicationParticipantShortInfo"/>
                                                <br/>
                                            </xsl:for-each>
                                        </td>
                                        <td class="up">
                                            <xsl:for-each select="oos:admittedInfo/oos:conditionsScoring/oos:conditionScoring">
                                                <xsl:call-template name="CostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:costCriterion[oos:criterionCode='CP']"/>
                                                    <xsl:with-param name="name" select="'Цена контракта'"/>
                                                </xsl:call-template>
                                                <xsl:call-template name="CostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:costCriterion[oos:criterionCode='MC']"/>
                                                    <xsl:with-param name="name" select="'Расходы на эксплуатацию и ремонт товаров, использование результатов работ'"/>
                                                </xsl:call-template>
                                                <xsl:call-template name="CostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:costCriterion[oos:criterionCode='TC']"/>
                                                    <xsl:with-param name="name" select="'Стоимость жизненного цикла товара или созданного в результате выполнения работы объекта'"/>
                                                </xsl:call-template>
                                                <xsl:call-template name="CostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:costCriterion[oos:criterionCode='EN']"/>
                                                    <xsl:with-param name="name" select="'Предложение о сумме соответствующих расходов заказчика, которые заказчик осуществит или понесет по энергосервисному контракту'"/>
                                                </xsl:call-template>
                                                <xsl:call-template name="NonCostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:qualitativeCriterion[oos:criterionCode='QO']"/>
                                                    <xsl:with-param name="name" select="'Квалификация участников закупки, в том числе наличие у них финансовых ресурсов, на праве собственности или ином законном основании оборудования и других материальных ресурсов, опыта работы, связанного с предметом контракта, и деловой репутации, специалистов и иных работников определенного уровня'"/>
                                                </xsl:call-template>
                                                <xsl:call-template name="NonCostCriterion">
                                                    <xsl:with-param name="criterion" select="oos:qualitativeCriterion[oos:criterionCode='QF']"/>
                                                    <xsl:with-param name="name" select="'Качественные, функциональные и экологические характеристики объекта закупки'"/>
                                                </xsl:call-template>
                                                <br/>
                                            </xsl:for-each>
                                        </td>
                                        <td class="centered up">
                                            <xsl:value-of select="oos:admittedInfo/oos:score"/>
                                        </td>
                                        <td class="centered up">
                                            <xsl:value-of select="oos:admittedInfo/oos:appRating"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </xsl:otherwise>
                        </xsl:choose>
                    </table>
                </xsl:if>
            </xsl:for-each>
        </xsl:if>
    </xsl:template>

    <xsl:template name="MeasurementOrderRecord">
        <xsl:param name="value" select="''"/>
        <xsl:variable name="measurement">
            <xsl:choose>
                <xsl:when test="$value = 'F'">Лучшим условием исполнения контракта по критерию
                    оценки (показателю) является наибольшее значение критерия (показателя)
                </xsl:when>
                <xsl:when test="$value = 'L'">Лучшим условием исполнения контракта по критерию
                    оценки (показателю) является наименьшее значение критерия (показателя)
                </xsl:when>
                <xsl:when test="$value = 'O'">Оценка производится по шкале оценки или другому
                    порядку, указанному в документации
                </xsl:when>
            </xsl:choose>
        </xsl:variable>
        <xsl:call-template name="UnderlinedInfoRecord1">
            <xsl:with-param name="header" select="$measurement"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="CostCriterion">
        <xsl:param name="criterion"/>
        <xsl:param name="name"/>
        <xsl:if test="count($criterion)&gt;0">
            <p>
                <b>
                    <xsl:value-of select="$name"/>
                </b>
            </p>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header">
                    <xsl:text>Значимость критерия оценки: </xsl:text>
                    <xsl:call-template name="currency">
                        <xsl:with-param name="x" select="$criterion/oos:value"/>
                    </xsl:call-template>
                    <xsl:text>%</xsl:text>
                </xsl:with-param>
            </xsl:call-template>
            <xsl:if test="$criterion/oos:addInfo">
                <xsl:call-template name="UnderlinedInfoRecord1">
                    <!-- Дополнительная информация о содержании и порядке оценки по критерию -->
                    <xsl:with-param name="header" select="$criterion/oos:addInfo"/>
                </xsl:call-template>
            </xsl:if>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="'Предложение участника: '"/>
                <xsl:with-param name="value">
                    <xsl:if test="count($criterion/oos:offer)&gt;0">
                        <xsl:call-template name="currency">
                            <xsl:with-param name="x" select="$criterion/oos:offer"/>
                        </xsl:call-template>
                        <xsl:text> </xsl:text>
                        <xsl:variable name="lotInfo" select="../../../../../oos:lotInfo"/>
                        <xsl:choose>
                            <xsl:when test="$lotInfo/oos:isMaxPriceCurrency!=''">
                                <xsl:value-of select="$lotInfo/oos:isMaxPriceCurrency/common:currency/base:name"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="$lotInfo/oos:currency/oos:name"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:if>
                </xsl:with-param>
            </xsl:call-template>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="$criterion/oos:offerInfo"/>
            </xsl:call-template>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header" select="'Оценка заявки по критерию: '"/>
                <xsl:with-param name="value">
                    <xsl:if test="count($criterion/oos:normedScore)&gt;0">
                        <xsl:value-of select="$criterion/oos:normedScore"/>
                    </xsl:if>
                </xsl:with-param>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>

    <xsl:template name="NonCostCriterion">
        <xsl:param name="criterion"/>
        <xsl:param name="name"/>
        <xsl:if test="count($criterion)&gt;0">
            <p>
                <b>
                    <xsl:value-of select="$name"/>
                </b>
            </p>
            <xsl:call-template name="UnderlinedInfoRecord1">
                <xsl:with-param name="header">
                    <xsl:text>Значимость критерия оценки: </xsl:text>
                    <xsl:call-template name="currency">
                        <xsl:with-param name="x" select="$criterion/oos:value"/>
                    </xsl:call-template>
                    <xsl:text>%</xsl:text>
                </xsl:with-param>
            </xsl:call-template>
            <xsl:if test="$criterion/oos:addInfo">
                <xsl:call-template name="UnderlinedInfoRecord1">
                    <!-- Дополнительная информация о содержании и порядке оценки по критерию -->
                    <xsl:with-param name="header" select="$criterion/oos:addInfo"/>
                </xsl:call-template>
            </xsl:if>
            <xsl:if test="count($criterion/oos:normedScore)&gt;0">
                <xsl:call-template name="UnderlinedInfoRecord1">
                    <xsl:with-param name="header" select="concat('Оценка заявки по критерию: ', $criterion/oos:normedScore)"/>
                </xsl:call-template>
            </xsl:if>
            <xsl:choose>
                <xsl:when test="$criterion/oos:criterionHaveIndicator='true'">
                    <xsl:call-template name="NonCostIndicators">
                        <xsl:with-param name="indicators" select="$criterion/oos:indicator"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:if test="count($criterion/oos:indicatorScoring)&gt;0">
                        <xsl:for-each-group select="$criterion/oos:indicatorScoring" group-by="oos:indicatorCode">
                            <xsl:variable name="code" select="current-grouping-key()"/>
                            <xsl:for-each select="current-group()[oos:indicatorCode=$code][1]">
                                <xsl:if test="oos:limit&gt;0">
                                    <xsl:call-template name="UnderlinedInfoRecord1">
                                        <xsl:with-param name="header" select="concat('Предельное значение: ', oos:limit)"/>
                                    </xsl:call-template>
                                </xsl:if>
                                <xsl:call-template name="UnderlinedInfoRecord1">
                                    <xsl:with-param name="header">
                                        <xsl:value-of select="'Порядок оценки по критерию: '"/>
                                        <xsl:call-template name="MeasurementOrderRecord">
                                            <xsl:with-param name="value" select="oos:measurementOrder"/>
                                        </xsl:call-template>
                                    </xsl:with-param>
                                </xsl:call-template>
                                <xsl:call-template name="UnderlinedInfoRecord1">
                                    <xsl:with-param name="header">
                                        <xsl:text>Предложение участника: </xsl:text>
                                        <xsl:if test="count(oos:offer)&gt;0">
                                            <xsl:call-template name="currency">
                                                <xsl:with-param name="x" select="oos:offer"/>
                                                <xsl:with-param name="format" select="'0.0000'"/>
                                            </xsl:call-template>
                                        </xsl:if>
                                    </xsl:with-param>
                                </xsl:call-template>
                                <!-- Информация о предложении участника -->
                                <xsl:if test="count(oos:offerInfo)&gt;0">
                                    <xsl:call-template name="UnderlinedInfoRecord1">
                                        <xsl:with-param name="header" select="concat('Информация о предложении участника: ', oos:offerInfo)"/>
                                    </xsl:call-template>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:for-each-group>
                    </xsl:if>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

    <xsl:template name="NonCostIndicators">
        <xsl:param name="indicators"/>
        <xsl:if test="count($indicators)&gt;0">
            <p>
                <b>
                    <i>
                        <xsl:value-of select="'Показатели критерия оценки:'"/>
                    </i>
                </b>
            </p>
            <xsl:for-each-group select="$indicators" group-by="oos:indicatorCode">
                <xsl:variable name="code" select="current-grouping-key()"/>
                <xsl:variable name="position" select="position()"/>

                <xsl:for-each select="current-group()[oos:indicatorCode=$code][1]">

                    <b>
                        <i>
                            <xsl:call-template name="UnderlinedInfoRecord1">
                                <xsl:with-param name="header" select="concat($position,' ', oos:name)"/>
                            </xsl:call-template>
                        </i>
                    </b>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header">
                            <xsl:text>Значимость показателя: </xsl:text>
                            <xsl:call-template name="currency">
                                <xsl:with-param name="x" select="oos:value"/>
                            </xsl:call-template>
                            <xsl:text>%</xsl:text>
                        </xsl:with-param>
                    </xsl:call-template>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header" select="concat('Предельное значение: ', oos:limit)"/>
                    </xsl:call-template>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header">
                            <xsl:value-of select="'Порядок оценки по критерию: '"/>
                            <xsl:call-template name="MeasurementOrderRecord">
                                <xsl:with-param name="value" select="oos:measurementOrder"/>
                            </xsl:call-template>
                        </xsl:with-param>
                    </xsl:call-template>
                    <xsl:call-template name="UnderlinedInfoRecord1">
                        <xsl:with-param name="header">
                            <xsl:text>Предложение участника: </xsl:text>
                            <xsl:if test="count(oos:offer)&gt;0">
                                <xsl:call-template name="currency">
                                    <xsl:with-param name="x" select="oos:offer"/>
                                    <xsl:with-param name="format" select="'0.0000'"/>
                                </xsl:call-template>
                            </xsl:if>
                        </xsl:with-param>
                    </xsl:call-template>
                    <!-- Информация о предложении участника -->
                    <xsl:if test="count(oos:offerInfo)&gt;0">
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="concat('Информация о предложении участника: ', oos:offerInfo)"/>
                        </xsl:call-template>
                    </xsl:if>
                    <xsl:if test="count(oos:normedScore)&gt;0">
                        <xsl:call-template name="UnderlinedInfoRecord1">
                            <xsl:with-param name="header" select="concat('Оценка заявки по показателю: ', oos:normedScore)"/>
                        </xsl:call-template>
                    </xsl:if>
                </xsl:for-each>
            </xsl:for-each-group>
        </xsl:if>
    </xsl:template>

    <xsl:template name="ApplicationParticipantsInfo">
        <xsl:for-each select="oos:appParticipants/oos:appParticipant">
            <xsl:call-template name="ApplicationParticipantShortInfo"/>
            <xsl:if test="not(ends-with(oos:participantType,'F')) or oos:inn!=''">
                <br/>
                <xsl:text>ИНН: </xsl:text>
                <xsl:value-of select="oos:inn"/>
            </xsl:if>
            <xsl:if test="oos:idNumber!=''">
                <br/>
                <xsl:text>Аналог ИНН: </xsl:text>
                <xsl:value-of select="oos:idNumber"/>
            </xsl:if>
            <xsl:if test="count(oos:inn)=0 and count(oos:idNumber)=0">
                <br/>
                <xsl:text>ИНН / Аналог ИНН _______________</xsl:text>
            </xsl:if>
            <xsl:if test="oos:kpp!=''">
                <br/>
                <xsl:text>КПП: </xsl:text>
                <xsl:value-of select="oos:kpp"/>
            </xsl:if>
            <br/>
            <xsl:text>Почтовый адрес: </xsl:text>
            <xsl:value-of select="oos:postAddress"/>
            <br/>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="ApplicationParticipantShortInfo">
        <xsl:choose>
            <xsl:when test="oos:participantType='U' or oos:participantType='UF'">
                <xsl:value-of select="oos:organizationName"/>
                <!--<xsl:if test="oos:participantType='U'">
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="oos:legalForm/oos:singularName"/>
                </xsl:if>-->
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="fio">
                    <xsl:value-of select="oos:contactInfo/oos:lastName"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="oos:contactInfo/oos:firstName"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="oos:contactInfo/oos:middleName"/>
                </xsl:variable>
                <xsl:if test="oos:participantType='B' or oos:participantType='BF'">
                    <xsl:text>Индивидуальный предприниматель </xsl:text>
                </xsl:if>
                <xsl:call-template name="printDefault">
                    <xsl:with-param name="value" select="$fio"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="dateWithoutTimeSpelledMonth">
        <xsl:param name="dateTime"/>
        <xsl:param name="format" select="'date'"/>
        <xsl:variable name="date" select="substring($dateTime,1,10 )"/>
        <xsl:variable name="year" select="substring-before($date, '-')"/>
        <xsl:variable name="month">
            <xsl:call-template name="SpellMonth">
                <xsl:with-param name="month" select="substring-before(substring-after($date, '-'), '-')"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="day" select="substring-after(substring-after($date, '-'), '-')"/>
        <xsl:variable name="hour" select="substring($dateTime,12,2 )"/>
        <xsl:variable name="minute" select="substring($dateTime,15,2 )"/>
        <xsl:if test="$dateTime!=''">
            <xsl:choose>
                <xsl:when test="$format='date'">
                    <xsl:value-of select="concat($day, ' ', $month, ' ', $year)"/>
                </xsl:when>
                <xsl:when test="$format='datetime'">
                    <xsl:value-of select="concat($day, ' ', $month, ' ', $year, ' года в ', $hour, ':', $minute, ' (по местному времени)')"/>
                </xsl:when>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

    <xsl:template name="SpellMonth">
        <xsl:param name="month"/>

        <xsl:choose>
            <xsl:when test="$month='01'">января</xsl:when>
            <xsl:when test="$month='02'">февраля</xsl:when>
            <xsl:when test="$month='03'">марта</xsl:when>
            <xsl:when test="$month='04'">апреля</xsl:when>
            <xsl:when test="$month='05'">мая</xsl:when>
            <xsl:when test="$month='06'">июня</xsl:when>
            <xsl:when test="$month='07'">июля</xsl:when>
            <xsl:when test="$month='08'">августа</xsl:when>
            <xsl:when test="$month='09'">сентября</xsl:when>
            <xsl:when test="$month='10'">октября</xsl:when>
            <xsl:when test="$month='11'">ноября</xsl:when>
            <xsl:when test="$month='12'">декабря</xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="UnderlinedInfoRecord1">
        <xsl:param name="header" select="''"/>
        <xsl:param name="value" select="''"/>
        <xsl:param name="dateType" select="''"/>
        <xsl:param name="hint" select="''"/>
        <xsl:param name="msg" select="''"/>
        <p>
            <xsl:if test="$header!=''">
                <xsl:value-of select="$header"/>
            </xsl:if>
            <xsl:choose>
                <xsl:when test="string($value)!='' or $msg!=''">
                    <u>
                        <xsl:call-template name="printDefault">
                            <xsl:with-param name="value" select="$value"/>
                            <xsl:with-param name="dateType" select="$dateType"/>
                            <xsl:with-param name="msg" select="$msg"/>
                        </xsl:call-template>
                    </u>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:if test="$hint != ''">
                        <span class="PFplaceholder">
                            <xsl:value-of select="$hint"/>
                        </span>
                    </xsl:if>
                </xsl:otherwise>
            </xsl:choose>
        </p>
    </xsl:template>

    <xsl:template name="Signatures">
        <xsl:param name="commission"/>
        <br/>
        <p>
            <xsl:text>Подписи членов комиссии:</xsl:text>
        </p>
        <xsl:if test="count($commission/oos:commissionMembers/oos:commissionMember)&gt;0">
            <table style="width: 100%;">
                <tr>
                    <th style="width:40%;"/>
                    <th style="width:30%;"/>
                    <th style="width:40%;"/>
                </tr>
                <xsl:for-each select="$commission/oos:commissionMembers/oos:commissionMember">
                    <tr>
                        <td style="text-align:left;word-wrap:break-word;">
                            <xsl:value-of select="oos:role/oos:name"/>
                        </td>
                        <td class="linedData"/>
                        <td style="text-align:right;word-wrap:break-word;">
                            <xsl:value-of select="oos:lastName"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="oos:firstName"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="oos:middleName"/>
                        </td>
                    </tr>
                    <tr style="height: 30px;">
                        <td/>
                        <td class="underlinedData">
                            <xsl:text>(Подпись)</xsl:text>
                        </td>
                        <td/>
                    </tr>
                </xsl:for-each>
            </table>
        </xsl:if>
    </xsl:template>

    <xsl:template name="DeliveryPlaceBlock">
        <xsl:param name="deliveryPlace"/>
        <xsl:param name="kladrPlaces"/>
        <xsl:choose>
            <xsl:when test="count($deliveryPlace)&gt;0">
                <xsl:value-of select="$deliveryPlace"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:for-each select="$kladrPlaces/oos:kladrPlace">
                    <xsl:if test="count(oos:kladr/oos:fullName)&gt;0">
                        <xsl:choose>
                            <xsl:when test="not(starts-with(lower-case(oos:kladr/oos:fullName), 'российская федерация'))">
                                <xsl:text>Российская Федерация, </xsl:text>
                                <xsl:choose>
                                    <xsl:when test="count(oos:noKladrForRegionSettlement)&gt;0">
                                        <xsl:value-of select="substring-before(oos:kladr/oos:fullName, ',')"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="oos:kladr/oos:fullName"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="oos:kladr/oos:fullName"/>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                    <xsl:if test="normalize-space(oos:country/oos:countryFullName)!=''">
                        <xsl:value-of select="oos:country/oos:countryFullName"/>
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                    <xsl:if test="normalize-space(oos:noKladrForRegionSettlement/oos:region)!=''">
                        <xsl:value-of select="oos:noKladrForRegionSettlement/oos:region"/>
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                    <xsl:if test="normalize-space(oos:noKladrForRegionSettlement/oos:settlement)!=''">
                        <xsl:value-of select="oos:noKladrForRegionSettlement/oos:settlement"/>
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                    <xsl:value-of select="oos:deliveryPlace"/>
                    <xsl:if test="position()!=last()">
                        <xsl:text> </xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
