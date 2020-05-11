package lmt.lamaintenduapi.Repository;

import lmt.lamaintenduapi.model.MaraudeUsers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin(maxAge = 3600)
public interface MaraudeUsersRepository extends CrudRepository<MaraudeUsers, Integer> {
    @Query("SELECT NEW lmt.lamaintenduapi.model.MaraudeUsers (mu.user, mu.maraude, mu.participate, mu.vehicule) " +
            "FROM MaraudeUsers mu " +
            "INNER JOIN User u ON mu.user = u " +
            "INNER JOIN Maraude m ON mu.maraude = m " +
            "INNER JOIN Lieu l ON m.lieu = l " +
            "WHERE mu.maraude.lieu.id = :lieuId ")
    List<MaraudeUsers> findAllMaraudeUsersByLieu(@RequestParam int lieuId);

    @Query("SELECT NEW lmt.lamaintenduapi.model.MaraudeUsers (mu.user, mu.maraude, mu.participate, mu.vehicule) " +
            "FROM MaraudeUsers mu " +
            "INNER JOIN User u ON mu.user = u " +
            "INNER JOIN Maraude m ON mu.maraude = m " +
            "INNER JOIN Lieu l ON m.lieu = l " +
            "WHERE mu.maraude.lieu.id = :lieuId AND (SUBSTRING(mu.maraude.date,  1, 10) = SUBSTRING(:date, 1, 10)) ")
    List<MaraudeUsers> findAllMaraudeUsersByLieuAndDate(@RequestParam int lieuId, @RequestParam String date);

    @Query("SELECT NEW lmt.lamaintenduapi.model.MaraudeUsers (mu.user, mu.maraude, mu.participate, mu.vehicule) " +
            "FROM MaraudeUsers mu " +
            "INNER JOIN User u ON mu.user = u " +
            "INNER JOIN Maraude m ON mu.maraude = m " +
            "WHERE mu.maraude.id = :maraudeId ")
    List<MaraudeUsers> findAllMaraudeUsersByMaraudeId(@RequestParam int maraudeId);

}
