package co.istad.suiii.fswd.sbapp;

import lombok.Builder;

@Builder
public record Student(
        String name,
        String gender,
        String course
) {}
