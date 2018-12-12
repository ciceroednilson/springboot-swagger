package br.com.ciceroednilson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ciceroednilson.model.PessoaModel;
import br.com.ciceroednilson.model.ResponseModel;
import br.com.ciceroednilson.repository.PessoaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository; 
	
	/**
	 * SALVAR UM NOVO REGISTRO
	 * @param pessoa
	 * @return
	 */
	@ApiOperation(
			value="Cadastrar uma nova pessoa", 
			response=ResponseModel.class, 
			notes="Essa operação salva um novo registro com as informações de pessoa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value="/pessoa", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public @ResponseBody ResponseEntity<ResponseModel> salvar(@RequestBody PessoaModel pessoa){
 
 
		try {
 
			this.pessoaRepository.save(pessoa);
 
			return new ResponseEntity<ResponseModel>(new ResponseModel(1,"Registro salvo com sucesso!"), HttpStatus.OK);
 
		}catch(Exception e) {
 
			return new ResponseEntity<ResponseModel>(new ResponseModel(0,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * @param pessoa
	 * @return
	 */
	@ApiOperation(
			value="Atualiza uma pessoa existente", 
			response=ResponseModel.class, 
			notes="Essa operação atualiza um registro com as informações de pessoa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value="/pessoa", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody PessoaModel pessoa){
		
		try {
			
			this.pessoaRepository.save(pessoa);		
			
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * @return
	 */
	@ApiOperation(
			value="Consulta todas pessoas", 
			response=ResponseModel.class, 
			notes="Essa operação consulta todas as pessoas cadastradas.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de pessoas",
					response=List.class
					)
	})
	@RequestMapping(value="/pessoa", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PessoaModel> consultar(){
		
		return this.pessoaRepository.findAll();
	}
	
	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@ApiOperation(
			value="Consulta uma pessoa por código", 
			response=ResponseModel.class, 
			notes="Essa operação consulta uma pessoa pelo seu código.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna a pessoa encontrada pelo código",
					response=PessoaModel.class
					)
	})
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PessoaModel buscar(@PathVariable("codigo") Integer codigo){
		
		return this.pessoaRepository.findOne(codigo);
	}
	
	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@ApiOperation(
			value="Exclui uma pessoa pelo código", 
			response=ResponseModel.class, 
			notes="Essa operação excluir um registro pelo código.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo){
		
		PessoaModel pessoaModel = pessoaRepository.findOne(codigo);
		
		try {
			
			pessoaRepository.delete(pessoaModel);
			
			return new ResponseModel(1, "Registro excluido com sucesso!");
			
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
	
}
