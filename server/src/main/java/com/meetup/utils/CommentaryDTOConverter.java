package com.meetup.utils;

import com.meetup.entities.Commentary;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.entities.dto.UserDTO;

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
     * @param userDTO User DTO.
     * @return CommentaryDisplayDTO.
     */
    public static CommentaryDisplayDTO convertToCommentaryDisplayDTO(
        final Commentary commentary,
        final UserDTO userDTO) {
        CommentaryDisplayDTO commentaryDisplayDTO = new CommentaryDisplayDTO();
        commentaryDisplayDTO.setId(commentary.getId());
        commentaryDisplayDTO.setAuthor(userDTO);
        commentaryDisplayDTO.setArticleID(commentary.getArticleID());
        commentaryDisplayDTO.setContents(commentary.getContents());
        commentaryDisplayDTO
            .setTimePosted(commentary.getTimePosted().toString());
        return commentaryDisplayDTO;
    }
}
