package attendance;

import java.util.List;

/**
 * A record to hold the results of an SGPA calculation to ensure consistency.
 * @param sgpa The calculated SGPA value.
 * @param courses The list of unique courses used in the calculation, with their marks updated.
 */
public record SgpaResult(double sgpa, List<Course> courses) {}
