package br.ufg.inf.es.integracao.cotacao;

import com.buscape.developer.result.type.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * Testes para a classe cotador buscape
 * 
 * @author victor
 */
public class CotadorBuscapeTest {

    private CotadorBuscape cotador;
    private Unmarshaller unmarshaller;

    @Before
    public void setUp() {
        cotador = new CotadorBuscape();
        unmarshaller = mock(Unmarshaller.class);

        cotador.setUnmarshaller(unmarshaller);
    }

    /**
     * Test of buscarOfertas method, of class CotadorBuscape.
     */
    @Test
    public void testBuscarOfertasValidas() throws JAXBException {
        Result result = mock(Result.class);
        Currency currency = mock(Currency.class);
        Offer offer = mock(Offer.class);
        PriceOffer priceOffer = mock(PriceOffer.class);
        Link link = mock(Link.class);
        Links links = mock(Links.class);
        Seller seller = mock(Seller.class);

        when(priceOffer.getCurrency()).thenReturn(currency);
        when(offer.getPrice()).thenReturn(priceOffer);
        when(offer.getLinks()).thenReturn(links);
        when(links.getLinks()).thenReturn(Arrays.asList(link));
        when(seller.getLinks()).thenReturn(links);
        when(offer.getSeller()).thenReturn(seller);
        when(unmarshaller.unmarshal(any(URL.class))).thenReturn(result);
        when(result.getOffers()).thenReturn(Arrays.asList(offer));

        Collection esperado = cotador.buscarOfertas("isbnteste");
        assertTrue(esperado.size() == 1);
    }

    /**
     * Test of buscarOfertas method, of class CotadorBuscape.
     */
    @Test
    public void testBuscarOfertasComErroNoUnMarshal() {
        Collection result = cotador.buscarOfertas("");
        assertTrue(result.size() == 0);
    }

    /**
     * Test of gerarUrlDeBusca method, of class CotadorBuscape.
     */
    @Test
    public void testGerarUrlDeBusca() {
        String isbn = "isbnteste";
        String result = cotador.gerarUrlDeBusca(isbn);
        assertTrue(result.endsWith(isbn));
    }
}
