import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { UserEntity } from "../../models/UserEntity";

async function runSeeds() {
    await AppDataSource.initialize();
    const userRepository = AppDataSource.getRepository(UserEntity);

    const emailChecker = "seed@email.com";
    const checkEmail = await userRepository.exists({ where: { email: emailChecker } });
    
    if (!checkEmail){
        await userRepository.save(
            userRepository.create([{
                name: "Seed User",
                email: emailChecker,
                password: "seedpassword",
            },{
                name: "Ana Silva",
                email: "ana.silva@email.com",
                password: "seedpassword1",
            },
            {
                name: "Bruno Costa",
                email: "bruno.costa@email.com",
                password: "seedpassword2",
            },
            {
                name: "Carlos Souza",
                email: "carlos.souza@email.com",
                password: "seedpassword3",
            },]),
        );
    }
    
    await AppDataSource.destroy();
}

runSeeds().catch(async (error) => {
    console.error(error);
    if (AppDataSource.isInitialized) await AppDataSource.destroy();

    process.exit(1);
});