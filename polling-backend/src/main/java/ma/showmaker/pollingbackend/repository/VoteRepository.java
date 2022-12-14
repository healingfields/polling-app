package ma.showmaker.pollingbackend.repository;

import ma.showmaker.pollingbackend.model.ChoiceVoteCount;
import ma.showmaker.pollingbackend.model.Poll;
import ma.showmaker.pollingbackend.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v where v.user.id = :userId and v.poll.id = :pollId")
    Vote findByUserIdAndPollId(@Param("userId") Long userId, @Param("pollId") Long pollId);

    @Query("SELECT count(v.id) FROM Vote v where v.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("SELECT v FROM Vote v where v.user.id= :userId and v.poll.id in :pollIds")
    List<Vote> findByUserIdAndPollIdIn(@Param("userId") Long userId, @Param("pollIds") List<Long> pollIds);

    @Query("SELECT v.poll.id FROM Vote v where v.user.id= :userId")
    Page<Long> findVotedPollIdsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT NEW ma.showmaker.pollingbackend.model.ChoiceVoteCount(v.choice.id, count(v.id)) from Vote v where v.poll.id = :pollId GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdAndGroupByChoiceId(@Param("pollId") Long pollId);

    @Query("SELECT NEW ma.showmaker.pollingbackend.model.ChoiceVoteCount(v.choice.id, count(v.id)) from Vote v where v.poll.id in :pollIds GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);


}