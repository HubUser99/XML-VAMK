<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:variable name="xmlDocument"
		select="document('assignment7_1.xml')" />
	<xsl:template match="/">
		<html>
			<meta charset="UTF-8" />
			<head>
				<style>
					table, tr, td
					{
					border: 1px
					solid black;
					}
				</style>
			</head>
			<body>
				<!-- Question 1 -->
				<h3>The list of all the information of videos whose discount is less
					or equal to 10. For this problem put the XML data inside the XSL
					file and access it</h3>
				<table>
					<xsl:for-each
						select="$xmlDocument/collection/videos/video">
						<xsl:if
							test="./price/@discount &lt; 10 or ./price/@discount = 10">
							<tr>
								<td>
									<xsl:value-of select="." />
								</td>
							</tr>
							<!-- <div> <xsl:for-each select="./@*"> <p> <xsl:value-of select="name(.)"/> 
								: <xsl:value-of select="."/> </p> </xsl:for-each> <xsl:for-each select="./*"> 
								<xsl:for-each select="./@*"> <p> <xsl:value-of select="name(.)"/> : <xsl:value-of 
								select="."/> </p> </xsl:for-each> <p> <xsl:value-of select="name(.)"/> : 
								<xsl:value-of select="."/> </p> </xsl:for-each> </div> -->
						</xsl:if>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>