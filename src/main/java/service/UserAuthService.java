package service;

//servisi nuk permban te dhena, duhet te jete klase statike qe ofron metoda qe na i perdorim

import models.User;
import repository.UserRepository;

import java.sql.SQLException;

public class UserAuthService {
    public static User login(String username, String password)throws SQLException {
        User user= UserRepository.getByUsername(username);
        if(user == null ){
            return null;
        }
        //kontrollimi i passwordit permes PasswordHasher class
        if(PasswordHasher.compareSaltedHash(password,user.getSalt(),user.getSaltedPassword())){
            //salted password -> u gjeneru hash i tij pi bjen saltedhash dmth se u bo gabim
            return user;
        }
        return null;
    }

    public static User register(
            String firstName, String lastName, int age, String username, String password
    ) throws SQLException{
        //create user
        //username, salt, saltedPasswordHash
        User user =UserRepository.getByUsername(username);
        if(user!=null){
            //throw new ResourceAlreadyExistsError()
            return null;
        }
        String salt =PasswordHasher.generateSalt();
        String saltedPasswordHash=PasswordHasher.generateSaltedHash(password,salt);
        //krijimi i userit
        user=new User(0,username,saltedPasswordHash,salt);
        UserRepository.insert(new User(0,username,saltedPasswordHash,salt));

        //create user profile

//        UserProfileRepository.insert{
//            new UserProfile(0, user.getId(),firstName,lastName);

        return user;
    }

}