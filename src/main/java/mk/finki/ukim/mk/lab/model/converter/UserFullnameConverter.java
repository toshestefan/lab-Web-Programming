package mk.finki.ukim.mk.lab.model.converter;

import mk.finki.ukim.mk.lab.model.convert.UserFullname;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {
    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(UserFullname UserFullname) {
        if (UserFullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (UserFullname.getSurname() != null
                && !UserFullname.getSurname().isEmpty()) {
            sb.append(UserFullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (UserFullname.getName() != null
                && !UserFullname.getName().isEmpty()) {
            sb.append(UserFullname.getName());
        }

        return sb.toString();
    }

    @Override
    public UserFullname convertToEntityAttribute(String dbUserFullname) {
        if (dbUserFullname == null || dbUserFullname.isEmpty()) {
            return null;
        }

        String[] pieces = dbUserFullname.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserFullname userFullname = new UserFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbUserFullname.contains(SEPARATOR)) {
            userFullname.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                userFullname.setName(pieces[1]);
            }
        } else {
            userFullname.setName(firstPiece);
        }

        return userFullname;
    }
    }

