package com.onemount.infrastructure.repo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author an.cantuong
 */
public final class SpecificationTemplate {

    private SpecificationTemplate() {
    }

    public static <E> Specification<E> isNull(String attr) {
        return (root, query, builder) -> builder.isNull(root.get(attr));
    }

    public static <E> Specification<E> eq(String attr, Object obj) {
        return (root, query, builder) -> isNull(obj) ? builder.and() : builder.equal(root.get(attr), obj);
    }

    // Equal & allow null
    public static <E> Specification<E> eqn(String attr, Object obj) {
        return (root, query, builder) -> isNull(obj) ? builder.isNull(root.get(attr)) : builder.equal(root.get(attr), obj);
    }

    public static <E> Specification<E> like(String attr, String value) {
        return (root, query, builder) -> isEmpty(value) ? builder.and() : builder.like(root.get(attr), "%" + value + "%");
    }

    public static <E> Specification<E> likeBegin(String attr, String value) {
        return (root, query, builder) -> isEmpty(value)
                ? builder.and()
                : builder.equal(root.get(attr).as(String.class), "%" + value);
    }

    public static <E> Specification<E> likeEnd(String attr, String value) {
        return (root, query, builder) -> isEmpty(value)
                ? builder.and()
                : builder.equal(root.get(attr).as(String.class), value + "%");
    }

    public static <E> Specification<E> in(String attr, Object... values) {
        return (root, query, builder) -> isEmptyArray(values) ? builder.and() : root.get(attr).in(values);
    }

    public static <E> Specification<E> notIn(String attr, Object... values) {
        return (root, query, builder) -> isEmptyArray(values) ? builder.and() : root.get(attr).in(values).not();
    }

    public static <E> Specification<E> ltDateTime(String attr, LocalDateTime dateTime) {
        return (root, query, builder) -> isNull(dateTime) ? builder.and() : builder.lessThan(root.get(attr), dateTime);
    }

    public static <E> Specification<E> leDateTime(String attr, LocalDateTime dateTime) {
        return (root, query, builder) -> isNull(dateTime) ? builder.and() : builder.lessThanOrEqualTo(root.get(attr), dateTime);
    }

    public static <E> Specification<E> geDateTime(String attr, LocalDateTime dateTime) {
        return (root, query, builder) -> isNull(dateTime) ? builder.and() : builder.greaterThanOrEqualTo(root.get(attr), dateTime);
    }

    public static <E> Specification<E> gtDateTime(String attr, LocalDateTime dateTime) {
        return (root, query, builder) -> isNull(dateTime) ? builder.and() : builder.greaterThan(root.get(attr), dateTime);
    }

    public static <E> Specification<E> betweenDateTime(String attr, LocalDateTime from, LocalDateTime to) {
        return (root, query, builder) -> isNull(from) || isNull(to) ? builder.and() : builder.between(root.get(attr), from, to);
    }

    public static LocalDateTime toDayBegin(LocalDate localDate) {
        return localDate == null ? null : localDate.atTime(0, 0, 0);
    }

    public static LocalDateTime toDayEnd(LocalDate localDate) {
        return localDate == null ? null : localDate.atTime(23, 59, 59);
    }

    private static boolean isEmptyArray(Object... values) {
        return values == null || values.length == 0;
    }

    private static boolean isNull(Object obj) {
        return obj == null;
    }

    private static boolean isEmpty(String value) {
        return ObjectUtils.isEmpty(value);
    }
}