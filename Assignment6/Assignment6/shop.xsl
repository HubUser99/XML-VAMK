<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<meta charset="UTF-8" />
			<head>
				<style>
					table {
					border-collapse: collapse;
					width: 500px;
					}

					table, td, th
					{
					border: 1px
					solid black;
					}
				</style>
			</head>
			<body>
				<!-- Question 1 -->
				<h1>The list of CDs including their attribute information</h1>
				<table>
					<xsl:for-each select="shop/digital_media/cds/cd">
						<tr>
							<th>Attributes:</th>
						</tr>
						<xsl:for-each select="@*">
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="concat( ., '')" />
								</td>
							</tr>
						</xsl:for-each>

						<xsl:for-each select="*">
							<xsl:choose>
								<xsl:when test="@*">
									<tr>
										<th>Attributes:</th>
									</tr>
									<tr>
										<xsl:for-each select="@*">
											<th>
												<xsl:value-of select="name(.)" />
											</th>
											<td>
												<xsl:value-of select="concat( ., '')" />
											</td>
										</xsl:for-each>
									</tr>
								</xsl:when>
							</xsl:choose>
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="." />
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</table>

				<!-- Question 2 -->
				<h1>The list of all the information of videos whose id is v10 or v30</h1>
				<table>
					<xsl:for-each select="shop/digital_media/videos/video">
						<xsl:if test="@id = 'v10' or @id = 'v30'">
							<tr>
								<th>Attributes:</th>
							</tr>
							<xsl:for-each select="@*">
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="concat( ., '')" />
									</td>
								</tr>
							</xsl:for-each>

							<xsl:for-each select="*">
								<xsl:choose>
									<xsl:when test="@*">
										<tr>
											<th>Attributes:</th>
										</tr>
										<tr>
											<xsl:for-each select="@*">
												<th>
													<xsl:value-of select="name(.)" />
												</th>
												<td>
													<xsl:value-of select="concat( ., '')" />
												</td>
											</xsl:for-each>
										</tr>
									</xsl:when>
								</xsl:choose>
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="." />
									</td>
								</tr>
							</xsl:for-each>
						</xsl:if>
					</xsl:for-each>
				</table>

				<!-- Question 3 -->
				<h1>A combined list of all the information of cds and videos, which has a price between 10 and 25 euros</h1>
				<table>
					<xsl:for-each select="shop/digital_media/videos/video | shop/digital_media/cds/cd">
						<xsl:if test="(price >=  10) and (price &lt; 25)">
							<tr>
								<th>Attributes:</th>
							</tr>
							<xsl:for-each select="@*">
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="concat( ., '')" />
									</td>
								</tr>
							</xsl:for-each>

							<xsl:for-each select="*">
								<xsl:choose>
									<xsl:when test="@*">
										<tr>
											<th>Attributes:</th>
										</tr>
										<tr>
											<xsl:for-each select="@*">
												<th>
													<xsl:value-of select="name(.)" />
												</th>
												<td>
													<xsl:value-of select="concat( ., '')" />
												</td>
											</xsl:for-each>
										</tr>
									</xsl:when>
								</xsl:choose>
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="." />
									</td>
								</tr>
							</xsl:for-each>
						</xsl:if>
					</xsl:for-each>
				</table>

				<!-- Question 4 -->
				<h1>A combined list of all the information of cds and videos so that they are sorted according to the country in ascending order and according to the price in descending order</h1>
				<table>
					<xsl:for-each select="shop/digital_media/videos/video | shop/digital_media/cds/cd">
						<xsl:sort select="country" data-type="text" order="ascending" />
						<xsl:sort select="price" data-type="number" order="descending" />
						<tr>
							<th>Attributes:</th>
						</tr>
						<xsl:for-each select="@*">
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="concat( ., '')" />
								</td>
							</tr>
						</xsl:for-each>

						<xsl:for-each select="*">
							<xsl:choose>
								<xsl:when test="@*">
									<tr>
										<th>Attributes:</th>
									</tr>
									<tr>
										<xsl:for-each select="@*">
											<th>
												<xsl:value-of select="name(.)" />
											</th>
											<td>
												<xsl:value-of select="concat( ., '')" />
											</td>
										</xsl:for-each>
									</tr>
								</xsl:when>
							</xsl:choose>
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="." />
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</table>

				<!-- Question 5 -->
				<h1>A combined list of all the information of cds and videos, which are published either in Findland or after year 2005</h1>
				<table>
					<xsl:for-each select="shop/digital_media/videos/video | shop/digital_media/cds/cd">
						<xsl:if test="country = 'Finland' or publication_year > 2005">
							<tr>
								<th>Attributes:</th>
							</tr>
							<xsl:for-each select="@*">
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="concat( ., '')" />
									</td>
								</tr>
							</xsl:for-each>

							<xsl:for-each select="*">
								<xsl:choose>
									<xsl:when test="@*">
										<tr>
											<th>Attributes:</th>
										</tr>
										<tr>
											<xsl:for-each select="@*">
												<th>
													<xsl:value-of select="name(.)" />
												</th>
												<td>
													<xsl:value-of select="concat( ., '')" />
												</td>
											</xsl:for-each>
										</tr>
									</xsl:when>
								</xsl:choose>
								<tr>
									<th>
										<xsl:value-of select="name(.)" />
									</th>
									<td>
										<xsl:value-of select="." />
									</td>
								</tr>
							</xsl:for-each>
						</xsl:if>
					</xsl:for-each>
				</table>

				<!-- Question 6 -->
				<h1>A combined list of all the information of cds and videos, which are published either in Findland or after year 2005</h1>
				<table>
					<xsl:for-each select="shop/digital_media/videos/video | shop/digital_media/cds/cd">
						<xsl:choose>
							<xsl:when test="price &lt; 10">
								<tr>
									<td>
										<h5>Reasonable</h5>
									</td>
								</tr>
							</xsl:when>
							<xsl:when test="price >= 10 and price &lt; 20">
								<tr>
									<td>
										<h5>Fair</h5>
									</td>
								</tr>
							</xsl:when>
							<xsl:when test="price >= 20">
								<tr>
									<td>
										<h5>Expensive</h5>
									</td>
								</tr>
							</xsl:when>
						</xsl:choose>
						<tr>
							<th>Attributes:</th>
						</tr>
						<xsl:for-each select="@*">
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="concat( ., '')" />
								</td>
							</tr>
						</xsl:for-each>

						<xsl:for-each select="*">
							<xsl:choose>
								<xsl:when test="@*">
									<tr>
										<th>Attributes:</th>
									</tr>
									<tr>
										<xsl:for-each select="@*">
											<th>
												<xsl:value-of select="name(.)" />
											</th>
											<td>
												<xsl:value-of select="concat( ., '')" />
											</td>
										</xsl:for-each>
									</tr>
								</xsl:when>
							</xsl:choose>
							<tr>
								<th>
									<xsl:value-of select="name(.)" />
								</th>
								<td>
									<xsl:value-of select="." />
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>