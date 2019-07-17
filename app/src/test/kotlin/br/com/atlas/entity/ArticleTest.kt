package br.com.atlas.entity

import br.com.atlas.entity.api.article.Article
import br.com.atlas.entity.api.article.Source
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(MockitoJUnitRunner::class)
class ArticleTest {

    companion object {
        val articleAPI = Article(
                source = Source(id = "crypto-coins-news", name = "Crypto Coins News"),
                author = "David Hundeyin",
                title = "Ripple CEO Defends XRP Following Washington, DC’s Crypto Response",
                description = "Ripple CEO Brad Garlinghouse has taken the opportunity to plug XRP as a solution to the concerns raised by Treasury Secretary Steven Mnuchin. Speaking on Twitter, Garlinghouse said that he hopes regulators recognize that not all cryptocurrencies are cut from …",
                url = "https://www.ccn.com/news/ripple-ceo-shills-xrp-following-washington-dcs-response-to-crypto/2019/07/16/",
                urlToImage = "https://cdn.ccn.com/wp-content/uploads/2019/07/brad-garlinghouse.jpg",
                publishedAt = "2019-07-16T20:15:34Z",
                content = "Ripple CEO Brad Garlinghouse has taken the opportunity to plug XRP as a solution to the concerns raised by Treasury Secretary Steven Mnuchin. Speaking on Twitter, Garlinghouse said that he hopes regulators recognize that not all cryptocurrencies are cut from … [+3372 chars]")
    }

    @Test
    fun `check entity api to domain`() {
        val articleDomain = articleAPI.toDomain()
        assertEquals(articleAPI.toString(), articleDomain.toString())
    }

    @Test
    fun `check entity domain to view`() {
        val articleDomain = articleAPI.toDomain()
        val articleView = articleDomain.toView()
        assertEquals(articleDomain.toString(), articleView.toString())
    }

    @Test
    fun `check entity api to view`() {
        val articleDomain = articleAPI.toDomain()
        val articleView = articleDomain.toView()
        assertEquals(articleAPI.toString(), articleView.toString())
    }

}
