package co.edu.unicauca.microservicio_gestion_articulos.capaControladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.services.IArticleService;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.AuthorDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.EvaluatorDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",  
 methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}) 
public class ArticleRestController {
    
    @Autowired
    private IArticleService articuloService;
    
    /*El primer servicio recibe un artículo a registrar y retorna el articulo registrado */
    @PostMapping("/articles")
    public ArticleDTO registrarArticulo(@RequestBody ArticleDTO article){
        ArticleDTO objArticle = null;
        objArticle = articuloService.save(article);
        return objArticle;
    }

    /*El segundo servicio REST recibe el ID del articulo y retorna el artículo que corresponde con el ID. */
    @GetMapping("/articles/{id}")
    public ArticleDTO consultarArticuloId(@PathVariable Integer id){
        ArticleDTO objArticle = null;
        objArticle = articuloService.findById(id);
        return objArticle;
    }
    
    /*El tercer servicio REST no recibe datos y retorna una lista de los artículos registrados */
    @GetMapping("/articles")
    public List<ArticleDTO> listarArticulos(){
        return articuloService.findAll();
    }

    /*El cuarto servicio REST recibe el ID del artículo a actualizar y los nuevos datos del artículo, y retorna el
    artículo actualizado */
    @PutMapping("/articles/{id}")
    public ArticleDTO actualizarArticulo(@RequestBody ArticleDTO article, @PathVariable Integer id){
        ArticleDTO objArticle = null;
        ArticleDTO articuloActual = articuloService.findById(id);
        if(articuloActual != null){
            objArticle = articuloService.update(id, article);
        }
        return objArticle;
    }

    /*El quinto servicio REST recibe el ID del articulo a eliminar y retorna true o false si se eliminó.*/    
    @DeleteMapping("/articles/{id}")
    public Boolean eliminarArticulo(@PathVariable Integer id){
        boolean band = false;
        ArticleDTO articuloActual = articuloService.findById(id);
        if(articuloActual != null){
            band = articuloService.delete(id);
        }
        return band;
    }

    /*El sexto servicio REST recibe el ID del articulo a consultar y retorna true o false si existe.*/
    @GetMapping("/articles2")
    public Boolean consultarArticulo2(@RequestParam Integer id) {
        ArticleDTO articuloActual = articuloService.findById(id);
        if(articuloActual != null){
            return true;
        }
        return false;
    }

    /*Obtiene los autores de un articulo especifico*/
    @GetMapping("/articles/authors/{idArticle}")
    public List<AuthorDTO> getAuthorsByArticle(@PathVariable Integer idArticle) {
        return articuloService.getAuthorsByArticleId(idArticle);
    }
    
    /*Obtiene los evaluadores de un articulo especifico*/
    @GetMapping("/articles/evaluators/{idArticle}")
    public List<EvaluatorDTO> getEvaluatorsByArticle(@PathVariable Integer idArticle) {
        return articuloService.getEvaluatorsByArticleId(idArticle);
    }

    /*Obtiene los articulos de un usuario por su id */
    @GetMapping("/articles/getArticlesByUser/{idUser}")
    public List<ArticleDTO> getArticlesByUser(@PathVariable Integer idUser) {
        return articuloService.getArticlesByUserId(idUser);
    }
}

