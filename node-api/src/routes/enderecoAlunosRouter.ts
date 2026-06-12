import { Router } from 'express';
import { AppDataSource } from '../database/data-source';
import { EnderecoAlunoEntity } from '../models/EnderecoAlunoEntity';
import { EnderecoAlunoRepository } from '../repositories/EnderecoAlunoRepository';
import { EnderecoAlunoService } from '../services/EnderecoAlunoService';
import { EnderecoAlunoController } from '../controllers/EnderecoAlunoController';

const enderecoAlunoRouter = Router();
// Logica -> a rota da o caminho para o controller -> o controle chama o service -> que fazo service conversa com o repositories em a conexão com db
// Conceito basico: Variavel recebe o construtor que faz a conexão com o DB (AppDataSource) e buscar a tabela que precisa.
const enderecoAlunoRepository = new EnderecoAlunoRepository(AppDataSource.getRepository(EnderecoAlunoEntity));
const enderecoAlunoService = new EnderecoAlunoService(enderecoAlunoRepository);
const enderecoAlunoController = new EnderecoAlunoController(enderecoAlunoService)

enderecoAlunoRouter.get("/endal", enderecoAlunoController.listUser);
enderecoAlunoRouter.get("/endal/:id", enderecoAlunoController.findUserById);
enderecoAlunoRouter.post("/endal", enderecoAlunoController.register);
enderecoAlunoRouter.put("/endal/:id", enderecoAlunoController.updateUser);
enderecoAlunoRouter.delete("/endal/:id", enderecoAlunoController.deleteUser);

export default enderecoAlunoRouter;