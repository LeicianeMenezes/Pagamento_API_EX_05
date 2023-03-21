package uea.pagamentos_api.services;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uea.pagamentos_api.dto.ResumoPessoaDto;
import uea.pagamentos_api.models.Pessoa;
import uea.pagamentos_api.repositories.PessoaRepository;
import uea.pagamentos_api.repositories.filters.PessoaFilter;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Page<ResumoPessoaDto> resumir(PessoaFilter pessoaFilter,
			Pageable pageable){
		return pessoaRepository.filtrar(pessoaFilter, pageable);
	}
	
	public Pessoa buscarPorCodigo(Long codigo) {
		Pessoa pessoa = pessoaRepository.findById(codigo).orElseThrow();
		return pessoa;
	}
	
	public void excluir(Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	public Pessoa atualizarPropriedadeAtivo(Long codigo,
			Boolean ativo) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).
				orElseThrow();
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.
				findById(codigo).orElseThrow();
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
}
