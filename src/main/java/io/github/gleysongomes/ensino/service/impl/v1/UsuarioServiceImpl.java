package io.github.gleysongomes.ensino.service.impl.v1;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import io.github.gleysongomes.ensino.dto.v1.AtualizacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.CriacaoUsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.PageRequestDto;
import io.github.gleysongomes.ensino.dto.v1.PageResponseDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioDto;
import io.github.gleysongomes.ensino.dto.v1.UsuarioFilterDto;
import io.github.gleysongomes.ensino.exception.v1.NovaSenhaException;
import io.github.gleysongomes.ensino.exception.v1.RecursoNaoEncontradoException;
import io.github.gleysongomes.ensino.helper.v1.MessageSourceHelper;
import io.github.gleysongomes.ensino.mapper.v1.SimpleMapper;
import io.github.gleysongomes.ensino.model.v1.Usuario;
import io.github.gleysongomes.ensino.repository.v1.UsuarioRepository;
import io.github.gleysongomes.ensino.resource.v1.Message;
import io.github.gleysongomes.ensino.service.v1.UsuarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	private final SimpleMapper mapper;

	private final MessageSourceHelper messageSource;

	@Override
	@Transactional
	public UsuarioDto save(CriacaoUsuarioDto criacaoUsuarioDto) {
		var usuario = mapper.map(criacaoUsuarioDto, Usuario.class);

		usuario.setHashSenha(criacaoUsuarioDto.getSenha());
		usuario.setAtivo(Boolean.FALSE);
		usuario.setIdUsuarioCriacao(1L);
		usuario.setIdUsuarioAtualizacao(1L);
		usuario.setDataCadastro(ZonedDateTime.now());

		usuario = usuarioRepository.save(usuario);

		return mapper.map(usuario, UsuarioDto.class);
	}

	@Override
	public UsuarioDto findById(Long id) {
		var usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException(messageSource.getMessage(Message.USUARIO_NAO_ENCONTRADO)));
		return mapper.map(usuario, UsuarioDto.class);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		var usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException(messageSource.getMessage(Message.USUARIO_NAO_ENCONTRADO)));

		usuarioRepository.delete(usuario);
	}

	@Override
	@Transactional
	public UsuarioDto update(Long id, AtualizacaoUsuarioDto atualizacaoUsuarioDto) {
		var usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException(messageSource.getMessage(Message.USUARIO_NAO_ENCONTRADO)));

		if (StringUtils.hasText(atualizacaoUsuarioDto.getNovaSenha())
				&& StringUtils.hasText(atualizacaoUsuarioDto.getConfirmacaoNovaSenha())) {
			if (atualizacaoUsuarioDto.getNovaSenha().equals(atualizacaoUsuarioDto.getConfirmacaoNovaSenha())) {
				usuario.setHashSenha(atualizacaoUsuarioDto.getNovaSenha());
			} else {
				throw new NovaSenhaException(
						messageSource.getMessage(Message.NOVA_SENHA_NAO_IGUAL_CONFIRMAÇÃO_NOVA_SENHA));
			}
		}

		mapper.map(atualizacaoUsuarioDto, usuario);

		usuario.setDataAtualizacao(ZonedDateTime.now());
		usuario = usuarioRepository.save(usuario);

		return mapper.map(usuario, UsuarioDto.class);
	}

	@Override
	public PageResponseDto<UsuarioDto> findAll(UsuarioFilterDto usuarioFilterDto, PageRequestDto pageRequestDto) {
		var usuarios = usuarioRepository.findAll(usuarioFilterDto, pageRequestDto);
		var total = usuarioRepository.count(usuarioFilterDto, pageRequestDto);

		return PageResponseDto.<UsuarioDto>builder().page(pageRequestDto.getPage()).size(pageRequestDto.getSize())
				.totalElements(total).content(mapper.map(usuarios, UsuarioDto.class)).build();
	}

}
