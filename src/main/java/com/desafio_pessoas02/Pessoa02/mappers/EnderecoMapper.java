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

    @Mapping(target = "rua", expression = "java(request.rua() != null ? request.rua().toLowerCase() : null)")
    @Mapping(target = "bairro", expression = "java(request.bairro() != null ? request.bairro().toLowerCase() : null)")
    @Mapping(target = "cidade", expression = "java(request.cidade() != null ? request.cidade().toLowerCase() : null)")
    @Mapping(target = "estado", expression = "java(request.estado() != null ? request.estado().toLowerCase() : null)")
    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco toUpdade(@MappingTarget Endereco endereco, EnderecoAtualizado atualizacoes);
    EnderecoResponse toResponse(Endereco endereco);
    List<EnderecoResponse> toResponse(List<Endereco> enderecos);
}