<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Super oferta!!!</h1>
                <a> Firma o opisie:
                    <xsl:value-of select="OfferType/officeData"/>
                </a>
                <a style="color:blue;"> Opis:
                    <xsl:value-of select="OfferType/description"/>
                </a>
                <a style="color:blue;"> Cena:
                    <xsl:value-of select="OfferType/price"/>
                </a>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>