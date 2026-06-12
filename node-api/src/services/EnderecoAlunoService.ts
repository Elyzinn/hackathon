import { hash } from "bcrypt";
import { EnderecoAlunoRepository, IEnderecoAlunoRepository } from "../repositories/EnderecoAlunoRepository";
import {  EnderecoAlunoEntity } from "../models/EnderecoAlunoEntity";
import AppError from "../utils/AppErrors";

export class EnderecoAlunoService{
    constructor(private readonly endal: IEnderecoAlunoRepository){}

    async getEnderecoByCidade(cidade: string): Promise<EnderecoAlunoRepository | undefined>{
        const cidadeAluno = this.endal.getEnderecoByCidade(cidade);
        if(!cidadeAluno){
            throw new AppError(404, "Nenhum endereço encontrado");
        
        }
        return;

        
    }

    async findUserById(id: number): Promise<PublicUser>{
        const user = await this.users.getUserById(id);
        if(!user){
            throw new AppError(404, "User Not Found");
        }
        const { password: _p, ...userPublic} = user;
        return userPublic;
    }

    async registerUser(input: {name: string; email: string; password: string}):Promise<PublicUser> {
        const exists = await this.users.getUserByEmail(input.email);
        if(exists){
            throw new AppError(409,"E-mail already exists");
        }
        const passwordHash = await hash(input.password, 8);
        return this.users.createUser({
            name: input.name,
            email: input.email,
            password: passwordHash,
        });
    }

    async update(
        id: number,
        data: Partial<{name: string; email: string; password: string}>,
    ): Promise<PublicUser> {
        const user = await this.users.getUserById(id);
        if (!user) {
            throw new AppError(404,"User Not Found");
        }
        if(data.email && data.email !== user.email){
            const another = await this.users.getUserByEmail(data.email);
            if(another){
                throw new AppError(409, "E-mail already exists");
            }
        }

        if(data.name !== undefined) user.name = data.name;
        if(data.email !== undefined) user.email = data.email;
        if(data.password){
            user.password = await hash(data.password, 8);
        }

        return this.users.saveUser(user);
    }

    async deleteUser(id: number): Promise<void>{
        const ok = await this.users.deleteUser(id);
        if(!ok){
            throw new AppError(404, "User not found");
        }
    }
}