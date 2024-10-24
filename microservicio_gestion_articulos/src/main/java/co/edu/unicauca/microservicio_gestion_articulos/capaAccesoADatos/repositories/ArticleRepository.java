package co.edu.unicauca.microservicio_gestion_articulos.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import co.edu.unicauca.microservicio_gestion_articulos.capaAccesoADatos.models.ArticleEntity;


@Repository
public class ArticleRepository {
    private ArrayList<ArticleEntity> listaDeArticulos;
	private int pos;

	public ArticleRepository() {
		this.listaDeArticulos = new ArrayList<ArticleEntity>();
		pos = this.listaDeArticulos.size()+1;
	}

	/**
	 * @brief Retorna toda la lista de articulos
	 * @return lista de articulos
	 */
	public List<ArticleEntity> findAll() {
		System.out.println("Invocando a listar todos articulos");
		return this.listaDeArticulos;
	}

	/**
	 * @brief Retorna un articulo especifico
	 * @param id id del articulo
	 * @return articulo especifico
	 */
	public ArticleEntity findById(Integer id) {
		System.out.println("Invocando a consultar un articulo por id");
		ArticleEntity objArticulo = null;
		
		for (ArticleEntity articulo : listaDeArticulos) {
			if (articulo.getId() == id) {
				objArticulo = articulo;
				break;
			}
		}
		return objArticulo;
	}

	/**
	 * @brief almacenar articulo
	 * @param articulo articulo
	 * @return retorna el articulo almacenado
	 */
	public ArticleEntity save(ArticleEntity articulo) {
		System.out.println("Invocando a almacenar un articulo");
		articulo.setId(pos);
		ArticleEntity objArticulo = null;
		if (this.listaDeArticulos.add(articulo)) {
			objArticulo = articulo;
			pos++;
		}

		return objArticulo;
	}

	/**
	 * @brief actualza un articulo especifico
	 * @param id
	 * @param articulo
	 * @return retorna el articulo actualizado
	 */
	public ArticleEntity update(Integer id, ArticleEntity articulo) {
		System.out.println("Invocando a actualizar un articulo");
		ArticleEntity objArticulo = null;

		for (int i = 0; i < this.listaDeArticulos.size(); i++) {
			if (this.listaDeArticulos.get(i).getId() == id) {
				this.listaDeArticulos.set(i, articulo);
				objArticulo = articulo;
				break;
			}
		}

		return objArticulo;
	}

	/**
	 * @brief Elimina un articulo especifico
	 * @param id
	 * @return true/false 
	 */
	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar un articulo");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeArticulos.size(); i++) {
			if (this.listaDeArticulos.get(i).getId() == id) {
				this.listaDeArticulos.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	/**
	 * @brief Retorna los articulos asociados a un usuario(Autor)
	 * @param userId id del usuario
	 * @return lista de articulos
	 */
	public List<ArticleEntity> getArticlesByUserId(Integer userId){
		List<ArticleEntity> articles = new ArrayList<ArticleEntity>();
		for(ArticleEntity art : listaDeArticulos){
			if(art.getIdUser() == userId){
				articles.add(art);
			}
		}
		return articles;
	}
	
	/* 
	private void cargarArticulos() {
		//creando conferencia

		ArticleEntity objArticulo1 = new ArticleEntity(1, "Articulo 1", "revista1", "David Chacón, Jorge Martinez", 2);
		this.listaDeArticulos.add(objArticulo1);
		ArticleEntity objArticulo2 = new ArticleEntity(2, "Fútbol", "revista2", "Kylian Mbappe, Jorge Martinez", 2);
		this.listaDeArticulos.add(objArticulo2);
		ArticleEntity objArticulo3 = new ArticleEntity(3, "Vallenato", "revista3", "Jorge Celedon", 1);
		this.listaDeArticulos.add(objArticulo3);
		ArticleEntity objArticulo4 = new ArticleEntity(4, "Giselle mi mujer", "revista4", "David Chacón", 1);
		this.listaDeArticulos.add(objArticulo4);
	}*/

}
