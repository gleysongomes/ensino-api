package io.github.gleysongomes.ensino.repository.v1.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import io.github.gleysongomes.ensino.dto.v1.PageRequestDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioFilterDto;
import io.github.gleysongomes.ensino.model.v1.Usuario;
import io.github.gleysongomes.ensino.model.v1.Usuario_;
import io.github.gleysongomes.ensino.repository.v1.UsuarioCustomRepository;
import io.github.gleysongomes.ensino.util.v1.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UsuarioCustomRepositoryImpl implements UsuarioCustomRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Usuario> findAll(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto) {
		var cb = manager.getCriteriaBuilder();
		var query = cb.createQuery(Usuario.class);
		var usuario = query.from(Usuario.class);
		var predicates = getPredicates(usuarioFilterDto, cb, usuario);

		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(cb.desc(usuario.get(Usuario_.id)));

		var typedQuery = manager.createQuery(query);

		typedQuery.setFirstResult(pageRequestDto.getPageStart());
		typedQuery.setMaxResults(pageRequestDto.getSize());

		return typedQuery.getResultList();
	}

	@Override
	public Long count(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto) {
		var cb = manager.getCriteriaBuilder();
		var query = cb.createQuery(Long.class);
		var usuario = query.from(Usuario.class);
		var predicates = getPredicates(usuarioFilterDto, cb, usuario);

		query.where(predicates.toArray(new Predicate[0]));
		query.select(cb.count(usuario));

		var typedQuery = manager.createQuery(query);

		return typedQuery.getSingleResult();
	}

	private List<Predicate> getPredicates(UsuarioFilterDto usuarioFilterDto, CriteriaBuilder cb,
			Root<Usuario> usuario) {
		var predicates = new ArrayList<Predicate>();

		if (StringUtils.hasText(usuarioFilterDto.getEmail())) {
			predicates.add(cb.like(cb.lower(usuario.get(Usuario_.email)),
					"%" + usuarioFilterDto.getEmail().toLowerCase() + "%"));
		}

		if (StringUtils.hasText(usuarioFilterDto.getNome())) {
			predicates.add(cb.like(cb.lower(usuario.get(Usuario_.nome)),
					"%" + usuarioFilterDto.getNome().toLowerCase() + "%"));
		}

		if (usuarioFilterDto.getDataCadastro() != null) {
			String dataCadastro = DateUtil.formatDate(usuarioFilterDto.getDataCadastro());
			Expression<String> dataCadastroExpression = cb.function("TO_CHAR", String.class,
					usuario.get(Usuario_.dataCadastro), cb.literal("DD/MM/YYYY"));
			predicates.add(cb.equal(dataCadastroExpression, dataCadastro));
		}

		if (usuarioFilterDto.getDataAtualizacao() != null) {
			String dataAtualizacao = DateUtil.formatDate(usuarioFilterDto.getDataAtualizacao());
			Expression<String> dataAtualizacaoExpression = cb.function("TO_CHAR", String.class,
					usuario.get(Usuario_.dataAtualizacao), cb.literal("DD/MM/YYYY"));
			predicates.add(cb.equal(dataAtualizacaoExpression, dataAtualizacao));
		}

		if (usuarioFilterDto.getAtivo() != null) {
			predicates.add(cb.equal(usuario.get(Usuario_.ativo), usuarioFilterDto.getAtivo()));
		}

		if (StringUtils.hasText(usuarioFilterDto.getFusoHorario())) {
			predicates.add(cb.equal(usuario.get(Usuario_.fusoHorario), usuarioFilterDto.getFusoHorario()));
		}

		return predicates;
	}

}
