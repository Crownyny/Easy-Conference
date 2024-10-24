package co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.ArticleDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.AuthorDTO;
import co.edu.unicauca.microservicio_gestion_articulos.fachadaServices.DTO.EvaluatorDTO;

public interface IArticleService {
	/**
	 * @brief Lista de todos los articulos
	 * @return lista de todos los articulos
	 */
    public List<ArticleDTO> findAll();

	/**
	 * @brief Buscar articulo especifico
	 * @param id
	 * @return articulo
	 */
	public ArticleDTO findById(Integer id);

	/**
	 * @brief Almacenar un articulo
	 * @param article
	 * @return articulo almacenado
	 */
	public ArticleDTO save(ArticleDTO article);

	/**
	 * @brief actualizar articulo
	 * @param id
	 * @param cliente
	 * @return articulo actulziado
	 */
	public ArticleDTO update(Integer id, ArticleDTO cliente);

	/**
	 * @brief Eliminar articulo
	 * @param id
	 * @return true/false
	 */
	public boolean delete(Integer id);

	/**
	 * @brief se comunica con el microservicio gestion de usuarios para obtener los autores de un articulo
	 * @param articleId
	 * @return Lista de autores de un articulo
	 */
	public List<AuthorDTO> getAuthorsByArticleId(Integer articleId);

	/**
	 * @brief se comunica con el microservicio gestion de usuarios para obtener los evaluadores de un articulo
	 * @param articleId
	 * @return lista de evaludores de un articulo
	 */
	public List<EvaluatorDTO> getEvaluatorsByArticleId(Integer articleId);

	/**
	 * @brief Obtiene los articulos de un usuario(autor)
	 * @param userId
	 * @return Lista de articulos de un autor
	 */
	public List<ArticleDTO> getArticlesByUserId(Integer userId);
}
