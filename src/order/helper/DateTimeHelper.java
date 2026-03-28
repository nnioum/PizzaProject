package order.helper;

import exception.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeHelper {
    private static final DateTimeFormatter CUSTOM_FORMAT =
            DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

    public static LocalDateTime parseToLocalDateTime(String value) throws ValidationException {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Дата не должна быть пустой");
        }

        try {
            return LocalDateTime.parse(value.trim(), CUSTOM_FORMAT).withSecond(0).withNano(0);

        } catch (DateTimeParseException e) {
            throw new ValidationException(
                    "Неправильный формат даты. Ожидаемый формат: ГГГГ-ММ-ДДTЧЧ:ММ (2026-03-04T09:30)"
            );
        }
    }
}
