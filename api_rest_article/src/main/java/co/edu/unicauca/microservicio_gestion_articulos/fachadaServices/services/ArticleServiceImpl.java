package co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.services;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.microservicio_gestion_articulos.capaAccesoADatos.models.ArticleEntity;
import co.edu.unicauca.microservicio_gestion_articulos.capaAccesoADatos.repositories.ArticleRepository;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.AuthorDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.EvaluatorDTO;
import reactor.core.publisher.Flux;

@Service
public class ArticleServiceImpl implements IArticleService{
    private ArticleRepository servicioAccesoBaseDatos;
	private UserService servicioUsuarios;
    private ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

    @Override
	public List<ArticleDTO> findAll() {

		List<ArticleEntity> articulosEntity = this.servicioAccesoBaseDatos.findAll();
		List<ArticleDTO> articulosDTO = this.modelMapper.map(articulosEntity, new TypeToken<List<ArticleDTO>>() {
		}.getType());
		return articulosDTO;
	}

    @Override
	public ArticleDTO findById(Integer id) {
		ArticleEntity objArticleEntity = this.servicioAccesoBaseDatos.findById(id);
		if(objArticleEntity == null){
			return null;
		}
		ArticleDTO articleDTO = this.modelMapper.map(objArticleEntity, ArticleDTO.class);
		return articleDTO;
	}

    @Override
	public ArticleDTO save(ArticleDTO articulo) {
		ArticleEntity articuloEntity = this.modelMapper.map(articulo, ArticleEntity.class);
		ArticleEntity objArticleEntity = this.servicioAccesoBaseDatos.save(articuloEntity);
		ArticleDTO articuloDTO = this.modelMapper.map(objArticleEntity, ArticleDTO.class);
		return articuloDTO;
	}

    @Override
	public ArticleDTO update(Integer id, ArticleDTO articulo) {
		ArticleEntity articleEntity = this.modelMapper.map(articulo, ArticleEntity.class);
		ArticleEntity articleEntityActualizado = this.servicioAccesoBaseDatos.update(id, articleEntity);
		ArticleDTO articleDTO = this.modelMapper.map(articleEntityActualizado, ArticleDTO.class);
		return articleDTO;
	}

    @Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
	public List<AuthorDTO> getAuthorsByArticleId(Integer articleId) {
		System.out.println("Invocando a consultar autores por id de un articulo");
		List<Integer> authors;
		ArticleEntity objArticleEntity = this.servicioAccesoBaseDatos.findById(articleId);
        /*Validar si el articulo existe*/
		if(objArticleEntity == null){
			System.out.println("El articulo no existe");
			return null;
		}
		authors = objArticleEntity.getListAuthors();
		Flux<AuthorDTO> authorsFlux = Flux.fromIterable(authors)
            .flatMap(this.servicioUsuarios::getAuthorById);
		
		return authorsFlux.collectList().block();
    }

	@Override
	public List<EvaluatorDTO> getEvaluatorsByArticleId(Integer articleId){
		System.out.println("Invocando a consultar evaluadores por id de un articulo");
		List<Integer> evaluators;
		ArticleEntity objArticleEntity = this.servicioAccesoBaseDatos.findById(articleId);
		/*Validar si el articulo existe */
		if(objArticleEntity == null){
			System.out.println("El articulo no existe");
			return null;
		}
		evaluators = objArticleEntity.getListEvaluators();
		Flux<EvaluatorDTO> evaluatorsFlux = Flux.fromIterable(evaluators)
            .flatMap(this.servicioUsuarios::getEvaluatorById);
		return evaluatorsFlux.collectList().block();
	}

	@Override
	public List<ArticleDTO> getArticlesByUserId(Integer userId){
		List<ArticleEntity> articles = this.servicioAccesoBaseDatos.getArticlesByUserId(userId);
		List<ArticleDTO> objArticleDTOs = this.modelMapper.map(articles, new TypeToken<List<ArticleDTO>>() {
		}.getType());
		return objArticleDTOs;
	}
}

