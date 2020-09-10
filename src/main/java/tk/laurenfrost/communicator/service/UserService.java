package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.AppUser;
import tk.laurenfrost.communicator.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    public void deleteUser(String login) {
        this.userRepository.deleteById(login);
    }

    public void setBoard(String login, String macAddress) {
        Board board = new Board();
        board.setMacAddress(macAddress);
        AppUser appUser = userRepository.getOne(login);
        appUser.setBoard(board);
        userRepository.save(appUser);
    }

}
