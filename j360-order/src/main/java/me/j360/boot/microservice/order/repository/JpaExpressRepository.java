

package me.j360.boot.microservice.order.repository;

import me.j360.boot.microservice.order.entity.Express;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
class JpaExpressRepository implements ExpressRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Express> findAll() {
		return this.entityManager.createQuery("SELECT n FROM Note n", Express.class)
				.getResultList();
	}

}
