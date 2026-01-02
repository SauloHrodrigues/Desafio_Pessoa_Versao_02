package com.desafio_pessoas02.Pessoa02.mappers;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "nome", expression = "java(request.nome() != null ? request.nome().toLowerCase() : null)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    Pessoa toEntity(PessoaRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pessoa toUpdade(@MappingTarget Pessoa pessoa, PessoaAtualizada atualizacoes);
    PessoaResponse toResponse(Pessoa pessoa);
    List<PessoaResponse> toResponse(List<Pessoa> pessoa);
}