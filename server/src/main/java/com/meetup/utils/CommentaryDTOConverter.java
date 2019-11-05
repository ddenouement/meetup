package com.meetup.utils;

import com.meetup.entities.Commentary;
import com.meetup.entities.*;
import com.meetup.entities.dto.CommentaryDisplayDTO;

/**
 * Commentary DTO converter class.
 */
public final class CommentaryDTOConverter {

    /**
     * Private utility class constructor.
     */
    private CommentaryDTOConverter() {

    }

    /**
     * Convert Commentary object to displayable DTO.
     *
     * @param commentary Commentary.
     * @param user User  .
     * @return CommentaryDisplayDTO.
     */
    public static CommentaryDisplayDTO convertToCommentaryDisplayDTO(
        final Commentary commentary,
        final User user ) {
        CommentaryDisplayDTO commentaryDisplayDTO = new CommentaryDisplayDTO();
        commentaryDisplayDTO.setId(commentary.getId());
        commentaryDisplayDTO.setAuthorID(user.getId());
        commentaryDisplayDTO.setAuthorLogin(user.getLogin());
        commentaryDisplayDTO.setArticleID(commentary.getArticleID());
        commentaryDisplayDTO.setContents(commentary.getContents());
        commentaryDisplayDTO
            .setTimePosted(commentary.getTimePosted());
        return commentaryDisplayDTO;
    }
}
