import { Router } from 'express';
import { AppDataSource } from '../database/data-source';
import { EnderecoEmpresaEntity } from '../models/EnderecoEmpresaEntity';
import { EnderecoEmpresaRepository } from '../repositories/EnderecoEmpresaRepository';
import { EnderecoEmpresaService } from '../services/EnderecoEmpresaService';
import { EnderecoEmpresaController } from '../controllers/EnderecoEmpresaController';

const enderecoEmpresaRouter = Router();
// Logica -> a rota da o caminho para o controller -> o controle chama o service -> que fazo service conversa com o repositories em a conexão com db
// Conceito basico: Variavel recebe o construtor que faz a conexão com o DB (AppDataSource) e buscar a tabela que precisa.
const enderecoAlunoRepository = new EnderecoAlunoRepository(AppDataSource.getRepository(EnderecoAlunoEntity));
const enderecoAlunoService = new EnderecoAlunoService(enderecoAlunoRepository);
const enderecoAlunoController = new EnderecoAlunoController(enderecoAlunoService)

enderecoEmpresaRouter.get("/endemp/:id", EnderecoEmpresaController.findById);
enderecoEmpresaRouter.post("/endemp", EnderecoEmpresaController.register);
enderecoEmpresaRouter.put("/endemp/:id", EnderecoEmpresaController.updateUser);
enderecoEmpresaRouter.delete("/endemp/:id", EnderecoEmpresaController.deleteUser);

export default enderecoEmpresaRouter;