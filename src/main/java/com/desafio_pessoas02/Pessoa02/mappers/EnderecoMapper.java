package com.desafio_pessoas02.Pessoa02.mappers;

import com.desafio_pessoas02.Pessoa02.dtos.*;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(target = "rua", expression = "java(normalize(request.rua()))")
    @Mapping(target = "bairro", expression = "java(normalize(request.bairro()))")
    @Mapping(target = "cidade", expression = "java(normalize(request.cidade()))")
    @Mapping(target = "estado", expression = "java(normalize(request.estado()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    Endereco toEntity(EnderecoRequest request);

    List<Endereco> toEntity(List<EnderecoRequest> request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco toUpdade(@MappingTarget Endereco endereco, EnderecoAtualizado atualizacoes);
    EnderecoResponse toResponse(Endereco endereco);
    List<EnderecoResponse> toResponse(List<Endereco> enderecos);

    default String normalize(String value) {
        return value == null ? null : value.toLowerCase();
    }
}