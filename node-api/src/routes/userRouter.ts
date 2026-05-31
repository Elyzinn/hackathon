import { Router } from 'express';
import z from 'zod';
import { AppDataSource } from '../database/data-source.js';
import { UserEntity } from '../models/UserEntity.js';

const userRouter = Router();

userRouter.post('/', async (req, res) => {
    try {
        const userSchema = z.object({
            name: z.string(),
            email: z.string().email(),
            password: z.string().min(6)
        });

        const data = userSchema.parse(req.body);
        const repository = AppDataSource.getRepository(UserEntity);
        const entity = repository.create(data);
        const user = await repository.save(entity);
        res.json({message: "Chama loco"}).status(201);
    } catch (error) {
        res.status(400).json({ error: 'Invalid user data' });
    }        

});

export default userRouter;