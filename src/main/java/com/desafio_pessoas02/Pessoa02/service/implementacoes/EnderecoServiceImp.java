package com.desafio_pessoas02.Pessoa02.service.implementacoes;

import com.desafio_pessoas02.Pessoa02.dtos.EnderecoAtualizado;
import com.desafio_pessoas02.Pessoa02.dtos.EnderecoRequest;
import com.desafio_pessoas02.Pessoa02.exceptions.EnderecoNaoEncontradoException;
import com.desafio_pessoas02.Pessoa02.mappers.EnderecoMapper;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import com.desafio_pessoas02.Pessoa02.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoServiceImp {

    private final EnderecoRepository repository;
    private EnderecoMapper mapper = EnderecoMapper.INSTANCE;

    protected Endereco criar(Endereco endereco, Pessoa pessoa) {
        endereco.setPessoa(pessoa);
        return repository.save(endereco);
    }
    protected List<Endereco> criar(List<EnderecoRequest> dto, Pessoa pessoa){
        validaLista(dto);
        List<Endereco> enderecos = new ArrayList<>();
        boolean principal= false;

        for(EnderecoRequest request:dto){
            Endereco endereco = mapper.toEntity(request);
            if(!principal && endereco.isEnderecoPrincipal()){
                principal=true;
            } else {
                endereco.setEnderecoPrincipal(false);
            }
            enderecos.add(criar(endereco,pessoa));
        }
        return enderecos;
    }

    protected void atualiza(List<EnderecoAtualizado>atualizacoes){
        if(atualizacoes == null){
            return;
        } else {
            for(EnderecoAtualizado atualizado:atualizacoes){
                Endereco endereco = buscar(atualizado.id());
                endereco = mapper.toUpdade(endereco,atualizado);
                repository.save(endereco);
            }
        }

    }

    protected void apagar(List<Endereco> enderecos){
        for(Endereco endereco:enderecos){
            repository.delete(endereco);
        }
    }
    protected void validaLista(List<EnderecoRequest>dto){
        if(dto.isEmpty()){
            throw new RuntimeException("Lista vazia");
        }
    }

    protected Endereco buscar(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new EnderecoNaoEncontradoException(id)
        );
    }
}
