package beans.validations.handler;

import lombok.Value;

import java.util.Comparator;

@Value
class FieldErrorJson implements Comparable<FieldErrorJson> {

    String fieldName;
    String message;
    String messageCode;

    private static final Comparator<String> nullSafeStringComparator = Comparator.nullsFirst(String::compareToIgnoreCase);

    // keep them ordered for predictability
    @Override
    public int compareTo(FieldErrorJson o) {
        int compareByField = nullSafeStringComparator.compare(fieldName, o.fieldName);
        if (compareByField != 0) return compareByField;
        int compareByMessage = nullSafeStringComparator.compare(message, o.message);
        if (compareByMessage != 0) return compareByMessage;
        return nullSafeStringComparator.compare(messageCode, o.messageCode);
    }

}
