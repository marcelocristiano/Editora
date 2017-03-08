package service;

import dao.LivroDao;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Livro;

/**
 * REST Web Service
 *
 * @author Marcelo
 */
@Path("livro")
public class LivroService {

    // cria um novo objeto do tipo LivroDAO.
    private LivroDao livroDao = new LivroDao();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LivroService
     */
    public LivroService() {
    }

    /**
     * Retrieves representation of an instance of service.LivroService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LivroService
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    // Metodo para inserir dados livro.
    @POST
    @Path("insertLivro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertLivro(Livro livro) {
        String msg;
        try {
            livroDao.insertLivro(livro);
            msg = "Dados gravados com sucesso";
        } catch (Exception e) {
            msg = "Erro ao gravar os dados!";
        }

        return msg;
    }

    // Metodo que lista dados de Livro do banco de dados.
    @Path("listaLivros")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> listaLivros() throws Exception {
        List<Livro> listaLivros = new ArrayList<>();
        try {
            listaLivros = livroDao.listaLivro();
        } catch (Exception e) {
        }
        return listaLivros;
    }

    // Metodo para buscar um dado por Id.
    @Path("/listaId/{idLivro}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Livro listaLivroId(@PathParam("idLivro") int idLivro) {
        Livro livro = new Livro();
        try {
            livro = livroDao.listaLivroId(idLivro);
        } catch (Exception e) {
        }
        return livro;
    }

    // Metodo para editar uma nota.
    @Path("/edita/{idLivro}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String editaLivro(Livro livro, @PathParam("idLivro") int idLivro) {
        String msg;
        try {
            livroDao.editaLivro(livro, idLivro);
            msg = "Livro editado com sucesso.";
        } catch (Exception ex) {
            msg = "Erro ao editar o livro!";
        }
        return msg;

    }

    // Metodo para deletar um nota.
    @Path("/delete/{idLivro}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String editaLivro(@PathParam("idLivro") int idLivro) {
        String msg;
        try {
            livroDao.deleteLivro(idLivro);
            msg = "Livro Excluido com sucesso.";
        } catch (Exception ex) {
            msg = "Erro ao deletar o livro!";
        }
        return msg;
    }

}
