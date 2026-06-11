import { Router } from 'express';
import { AppDataSource } from '../database/data-source';
import { UserEntity } from '../models/UserEntity';
import { UserRepository } from '../repositories/UserRepository';
import { UserService } from '../services/UserService';
import { UserController } from '../controllers/UserController';

const userRouter = Router();
// Logica -> a rota da o caminho para o controller -> o controle chama o service -> o service conversa com o repositories que fazem a conexão com db
const userRepository = new UserRepository(AppDataSource.getRepository(UserEntity));
const userService = new UserService(userRepository);
const userController = new UserController(userService)
userRouter.get("/users", userController.listUser);
userRouter.get("/users/:id", userController.findUserById);
userRouter.post("/users", userController.register);
userRouter.put("/users/:id", userController.updateUser);
userRouter.delete("/users/:id", userController.deleteUser);

export default userRouter;