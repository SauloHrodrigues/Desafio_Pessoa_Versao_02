package com.desafio_pessoas02.Pessoa02.service.implementacoes;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaIdadeResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.exceptions.PessoaNaoEncontradaException;
import com.desafio_pessoas02.Pessoa02.mappers.EnderecoMapper;
import com.desafio_pessoas02.Pessoa02.mappers.PessoaMapper;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import com.desafio_pessoas02.Pessoa02.repository.EnderecoRepository;
import com.desafio_pessoas02.Pessoa02.repository.PessoaRepository;
import com.desafio_pessoas02.Pessoa02.service.PessoaServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaServiceI {

    private final PessoaRepository repository;
    private final EnderecoServiceImp enderecoService;
    private PessoaMapper mapper = PessoaMapper.INSTANCE;

    @Transactional
    @Override
    public PessoaResponse criar(PessoaRequest dto) {
        Pessoa pessoa = mapper.toEntity(dto);
        pessoa = repository.save(pessoa);

        List<Endereco> enderecos = enderecoService.criar(dto.enderecos(),pessoa);

        pessoa.setEnderecos(enderecos);

        return mapper.toResponse(pessoa);
    }

    @Override
    public Page<PessoaResponse> listar(Pageable pageable) {
        Page<PessoaResponse> pessoas = repository.findAll(pageable).map(mapper::toResponse);
        return pessoas;
    }

    @Override
    public PessoaResponse atualizar(Long id, PessoaAtualizada atualizacoes) {
        Pessoa pessoa = buscar(id);
        enderecoService.atualiza(atualizacoes.enderecos());
        Pessoa pessoaAtualizada = mapper.toUpdade(pessoa,atualizacoes);
        pessoaAtualizada = repository.save(pessoaAtualizada);

        return mapper.toResponse(pessoaAtualizada);
    }

    @Override
    public PessoaIdadeResponse mostrarIdade(Long id) {
        Pessoa pessoa = buscar(id);
        return mapper.toPessoaIdadeResponse(pessoa);
    }

    @Override
    public void apagar(Long id) {
        Pessoa pessoa = buscar(id);
        enderecoService.apagar(pessoa.getEnderecos());
        repository.delete(pessoa);
    }

    protected Pessoa buscar(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new PessoaNaoEncontradaException(id)
        );
    }
}
