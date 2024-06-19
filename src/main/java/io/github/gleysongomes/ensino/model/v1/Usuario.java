package io.github.gleysongomes.ensino.model.v1;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_usuario", schema = "ensino")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")
	@SequenceGenerator(name = "sq_usuario", sequenceName = "ensino.sq_usuario", allocationSize = 1)
	private Long id;

	@Column(unique = true, nullable = false, length = 80)
	private String email;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(name = "hash_senha", nullable = false, length = 60)
	private String hashSenha;

	@Column(name = "data_cadastro", nullable = false)
	private ZonedDateTime dataCadastro;

	@Column(name = "data_atualizacao")
	private ZonedDateTime dataAtualizacao;

	@Column(name = "id_usuario_criacao", nullable = false)
	private Long idUsuarioCriacao;

	@Column(name = "id_usuario_atualizacao")
	private Long idUsuarioAtualizacao;

	@Column(nullable = false)
	private Boolean ativo;

	@Column(name = "fuso_horario", length = 50)
	private String fusoHorario;
}
