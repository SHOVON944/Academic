package attendance;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.io.image.ImageData; // Added import for ImageData
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject; // Added import for PdfImageXObject

import java.io.FileNotFoundException;
import java.io.IOException; // Added for PdfFontFactory exception
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator; // Added for sorting
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.BiConsumer; // Added for BiConsumer
import utils.ImageManager; // Added for ImageManager

import attendance.ReportType;
import attendance.OverallCgpaResult;
import attendance.Semester;
import attendance.SemesterConfig;

public class PdfGenerator {



        public static void generateStudyAndRoutineReport(DataStorage dataStorage, String dest, String studentName,
            String faculty, String studentId, String registrationNumber, String session, String signatureImagePath) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        final PdfFont helveticaFont = _createFontWithFallback();

        try {
            _addHeaderContent(document, dataStorage, studentName, faculty, studentId, registrationNumber, session,
                    signatureImagePath, ReportType.COMPREHENSIVE, null, null, helveticaFont);

            document.add(new Paragraph("University, Routine & Cash Report").setBold().setFont(helveticaFont)
                    .setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // 1. ALL Option 1 Data (University Comprehensive)
            generateComprehensiveUniversityReport(document, dataStorage, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // 2. ALL Option 2 Data (Daily Routine / Tasks)
            document.add(new Paragraph("\n--- DAILY ROUTINE (TASKS) ---").setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            
            // 2.1 Pending Tasks for Today
            List<Task> todayPendingTasks = dataStorage.getTasks().stream()
                    .filter(t -> t.getDate() != null && t.getDate().equals(LocalDate.now()))
                    .filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
            document.add(new Paragraph("\nPending Tasks for Today (" + LocalDate.now() + ")").setBold().setFont(helveticaFont).setFontSize(14));
            generateTaskReport(document, todayPendingTasks, ReportType.TASK_LIST_PENDING, helveticaFont);

            // 2.2 All Pending Tasks
            List<Task> allPendingTasks = dataStorage.getTasks().stream()
                    .filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
            document.add(new Paragraph("\nAll Pending & Incomplete Tasks").setBold().setFont(helveticaFont).setFontSize(14));
            generateTaskReport(document, allPendingTasks, ReportType.TASK_LIST_PENDING, helveticaFont);

            // 2.3 All Tasks History (View All)
            document.add(new Paragraph("\nAll Tasks History (View All)").setBold().setFont(helveticaFont).setFontSize(14));
            generateTaskReport(document, dataStorage.getTasks(), ReportType.TASK_LIST_ALL, helveticaFont);

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            // 3. ALL Option 3 Data (Cash Management)
            document.add(new Paragraph("\n--- CASH MANAGEMENT ---").setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));

            // 3.1 Money Manager (Funds)
            document.add(new Paragraph("\nMoney Manager (Funds)").setBold().setFont(helveticaFont).setFontSize(14));
            generateFundReport(document, dataStorage, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // 3.2 Expense Management
            document.add(new Paragraph("\nExpense Management").setBold().setFont(helveticaFont).setFontSize(14));
            generateExpenseReport(document, dataStorage, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // 3.3 Daily Buy Records
            document.add(new Paragraph("\nDAILY BUY RECORDS").setBold().setFont(helveticaFont).setFontSize(16)
                    .setFontColor(ColorConstants.DARK_GRAY).setTextAlignment(TextAlignment.CENTER).setUnderline());
            document.add(new Paragraph("\n")); // Space after heading
            generateDailyBuyReport(document, dataStorage, dataStorage.getDailyBuys(), helveticaFont);

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            // 4. ALL Option 4 Data (Action Report Log)
            document.add(new Paragraph("\n--- ACTION REPORT LOG ---").setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            generateActionLogReport(document, dataStorage.getActionHistory(), helveticaFont);

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            // 5. ALL Option 5 Data (Navigation Flow History)
            document.add(new Paragraph("\n--- NAVIGATION FLOW REPORT ---").setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            generateNavigationFlowReport(document, dataStorage.getNavigationHistory(), helveticaFont);

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            // 6. ALL Option 6 Data (JSON Data View)
            document.add(new Paragraph("\n--- DATA.JSON CONTENT VIEW ---").setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            generateDataJsonReport(document, helveticaFont);
            
        } catch (Exception e) {
            System.err.println("Error during PDF generation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void generateReport(DataStorage dataStorage, String dest, String studentName, String faculty,



                String studentId, String registrationNumber, String session, String signatureImagePath,



                ReportType reportType, Semester semester, List<Task> tasksToReport) throws FileNotFoundException {



    



                            PdfWriter writer = new PdfWriter(dest);



    



                            PdfDocument pdf = new PdfDocument(writer);



    



                            Document document = new Document(pdf, PageSize.A4.rotate());



    



                            document.setMargins(20, 20, 20, 20);



    



                    



    



                            final PdfFont helveticaFont = _createFontWithFallback();



    



            try {



                _addHeaderContent(document, dataStorage, studentName, faculty, studentId, registrationNumber, session,



                        signatureImagePath, reportType, semester, tasksToReport, helveticaFont);



    



                // --- Report Generation Logic ---



                _addReportContent(document, dataStorage, reportType, semester, tasksToReport, helveticaFont);



            } catch (Exception e) {



                System.err.println("Error during PDF content generation in generateReport: " + e.getMessage());



                e.printStackTrace();



                document.add(new Paragraph("Error generating report content: " + e.getMessage())



                        .setFontColor(ColorConstants.RED));



            }



    



            document.close();



        }

    public static void generateCombinedReports(DataStorage dataStorage, String dest, String studentName,
            String faculty, String studentId, String registrationNumber, String session, String signatureImagePath,
            boolean includeDailyBuyAndRegistrationFee, boolean includeActionLog, boolean includeNavigationFlow, List<DataStorage.ActionEntry> actionHistory,
            List<DataStorage.NavigationEvent> navigationHistory) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        final PdfFont helveticaFont = _createFontWithFallback();

        try {
            _addHeaderContent(document, dataStorage, studentName, faculty, studentId, registrationNumber, session,
                    signatureImagePath, ReportType.COMPREHENSIVE, null, null, helveticaFont);

            document.add(new Paragraph("Combined Reports Generated: " + LocalDate.now()).setBold().setFont(helveticaFont)
                    .setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // --- Conditionally add Daily Buy and Registration Fee reports ---
            if (includeDailyBuyAndRegistrationFee) {
                // Daily Buy Reporting Structure
                document.add(new Paragraph("\n--- Daily Buy Reports ---").setBold().setFont(helveticaFont).setFontSize(14));
                List<DailyBuy> allBuys = dataStorage.getDailyBuys();
                if (!allBuys.isEmpty()) {
                    // Today's Daily Buy Report
                    List<DailyBuy> todayBuys = allBuys.stream()
                            .filter(buy -> buy.getDate().isEqual(LocalDate.now()))
                            .collect(Collectors.toList());
                    document.add(
                            new Paragraph(
                                    "\n--- Today's Daily Buy Report (" + Utils.formatDateWithDay(LocalDate.now()) + ") ---")
                                    .setBold().setFont(helveticaFont).setFontSize(12));
                    generateDailyBuyReport(document, dataStorage, todayBuys, helveticaFont);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                    // Daily Buy Reports per Day (excluding today's date, as it's already reported)
                    Map<LocalDate, List<DailyBuy>> buysByDate = allBuys.stream()
                            .filter(buy -> !buy.getDate().isEqual(LocalDate.now()))
                            .collect(Collectors.groupingBy(DailyBuy::getDate));
                    List<LocalDate> sortedDates = buysByDate.keySet().stream().sorted().collect(Collectors.toList());

                    for (LocalDate date : sortedDates) {
                        List<DailyBuy> buysForDate = buysByDate.get(date);
                        document.add(
                                new Paragraph("\n--- Daily Buy Report for " + Utils.formatDateWithDay(date) + " ---").setBold()
                                        .setFont(helveticaFont).setFontSize(12));
                        generateDailyBuyReport(document, dataStorage, buysForDate, helveticaFont);
                        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    }

                    // Overall Daily Buy Report
                    document.add(new Paragraph("\n--- Overall Daily Buy Report ---").setBold().setFont(helveticaFont)
                            .setFontSize(14));
                    generateDailyBuyReport(document, dataStorage, allBuys, helveticaFont);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                } else {
                    document.add(new Paragraph("\nNo Daily Buy records found.").setFont(helveticaFont).setFontSize(12));
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }

                // Registration Fee Reports
                if (!dataStorage.getRegistrationFees().isEmpty()) {
                    document.add(new Paragraph("\n--- Registration Fee Reports ---").setBold().setFont(helveticaFont)
                            .setFontSize(14));
                    dataStorage.getRegistrationFees().keySet().stream().sorted(Comparator.naturalOrder())
                            .forEach(semester -> {
                                if (dataStorage.getRegistrationFees().containsKey(semester)
                                        && !dataStorage.getRegistrationFees().get(semester).isEmpty()) {
                                    try {
                                        document.add(new Paragraph("\nRegistration Fees for " + semester.getDisplayValue()).setBold()
                                                .setFont(helveticaFont).setFontSize(12));
                                        generateRegistrationFeeReport(document, dataStorage, semester, helveticaFont);
                                        document.add(new Paragraph("\n").setFont(helveticaFont));
                                    } catch (Exception e) {
                                        document.add(new Paragraph("Error generating registration fee report for "
                                                + semester.getDisplayValue() + ": " + e.getMessage()).setFont(helveticaFont)
                                                .setFontSize(8));
                                    }
                                }
                            });
                    document.add(new Paragraph("\n--- Overall Registration Fees ---").setBold().setFont(helveticaFont)
                            .setFontSize(12));
                    generateOverallRegistrationFeeReport(document, dataStorage, helveticaFont);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                } else {
                    document.add(new Paragraph("\nNo Registration Fee records found.").setFont(helveticaFont).setFontSize(12));
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }
            }

            // Task Reports
            document.add(new Paragraph("\n--- All Tasks Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            _addReportContent(document, dataStorage, ReportType.TASK_LIST_ALL, null, dataStorage.getTasks(), helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            document.add(new Paragraph("\n--- Completed Tasks Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            List<Task> completedTasks = dataStorage.getTasks().stream().filter(t -> t.getStatus() == TaskStatus.COMPLETED)
                    .collect(Collectors.toList());
            _addReportContent(document, dataStorage, ReportType.TASK_LIST_COMPLETED, null, completedTasks, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            document.add(new Paragraph("\n--- Pending & Incomplete Tasks Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            List<Task> pendingTasks = dataStorage.getTasks().stream().filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
            _addReportContent(document, dataStorage, ReportType.TASK_LIST_PENDING, null, pendingTasks, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            document.add(new Paragraph("\n--- Incomplete Tasks Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            List<Task> incompleteTasks = dataStorage.getTasks().stream().filter(t -> t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
            _addReportContent(document, dataStorage, ReportType.TASK_LIST_INCOMPLETE, null, incompleteTasks, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // University Reports
            List<Semester> allSemesters = dataStorage.getSemesterConfigurations().keySet().stream()
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            for (Semester semester : allSemesters) {
                SemesterConfig config = dataStorage.getSemesterConfigurations().get(semester);
                if (config != null && config.isEntered() && !config.getCourses().isEmpty()) {
                    document.add(new Paragraph("\n--- Attendance Calendar for " + semester.getDisplayValue() + " ---")
                            .setBold().setFont(helveticaFont).setFontSize(14));
                    _addReportContent(document, dataStorage, ReportType.ATTENDANCE_CALENDAR_SEMESTER, semester, null, helveticaFont);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                    document.add(new Paragraph("\n--- SGPA Report for " + semester.getDisplayValue() + " ---").setBold()
                            .setFont(helveticaFont).setFontSize(14));
                    _addReportContent(document, dataStorage, ReportType.CGPA_SEMESTER, semester, null, helveticaFont);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }
            }

            document.add(new Paragraph("\n--- Course List Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            _addReportContent(document, dataStorage, ReportType.COURSE_LIST, null, null, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            // Money Manager Report
            if (dataStorage.getFundAccounts() != null && !dataStorage.getFundAccounts().isEmpty()) {
                document.add(new Paragraph("\n--- Money Manager Report ---").setBold().setFont(helveticaFont).setFontSize(14));
                generateFundReport(document, dataStorage, helveticaFont);
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            // Expense Reports
            if (dataStorage.getDailyBuys() != null && !dataStorage.getDailyBuys().isEmpty()) {
                document.add(new Paragraph("\n--- Expense Management Report ---").setBold().setFont(helveticaFont).setFontSize(14));
                generateExpenseReport(document, dataStorage, helveticaFont);
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            document.add(new Paragraph("\n--- Overall CGPA Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            _addReportContent(document, dataStorage, ReportType.CGPA_OVERALL, null, null, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            
            // Attendance Summary Table
            generateAttendanceSummaryTable(document, dataStorage, helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            
            // Detailed Attendance Report
            document.add(new Paragraph("\n--- Detailed Attendance Report ---").setBold().setFont(helveticaFont).setFontSize(14));
            generateDetailedAttendanceReport(document, dataStorage, helveticaFont);
            
            // Action Log Report
            if (includeActionLog) {
                document.add(new Paragraph("\n--- Action Log Report ---").setBold().setFont(helveticaFont).setFontSize(14));
                generateActionLogReport(document, actionHistory, helveticaFont);
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            // Navigation Flow Report
            if (includeNavigationFlow) {
                document.add(new Paragraph("\n--- Navigation Flow Report ---").setBold().setFont(helveticaFont).setFontSize(14));
                generateNavigationFlowReport(document, navigationHistory, helveticaFont);
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

        } catch (Exception e) {
            System.err.println("Error during PDF content generation in generateCombinedReports: " + e.getMessage());
            e.printStackTrace();
            document.add(new Paragraph("Error generating combined report content: " + e.getMessage())
                    .setFontColor(ColorConstants.RED));
        } finally {
            document.close();
        }
    }

    // Helper method to add the common header content (student details, SHREB, etc.)

    private static void _addHeaderContent(Document document, DataStorage dataStorage, String studentName,
            String faculty, String studentId, String registrationNumber, String session, String signatureImagePath,
            ReportType reportType, Semester semester, List<Task> tasksToReport, PdfFont helveticaFont) {

        // Add a title based on report type (defined early to be used in header)

        String generatedReportTitle = "Report - " + LocalDate.now();

        switch (reportType) {

            case COMPREHENSIVE:

                generatedReportTitle = "Comprehensive Report - " + LocalDate.now();

                break;

            case ATTENDANCE_CALENDAR_SEMESTER:

                generatedReportTitle = "Attendance Calendar Report for "
                        + (semester != null ? semester.getDisplayValue() : "Selected Semester");

                break;

            case TASK_LIST_ALL:

                generatedReportTitle = "All Tasks Report - " + LocalDate.now();

                break;

            case TASK_LIST_PENDING:

                generatedReportTitle = "Pending Tasks Report - " + LocalDate.now();

                break;

            case TASK_LIST_COMPLETED:

                generatedReportTitle = "Completed Tasks Report - " + LocalDate.now();

                break;

            case TASK_LIST_INCOMPLETE:

                generatedReportTitle = "Incomplete Tasks Report - " + LocalDate.now();

                break;

            case CGPA_SEMESTER:

                generatedReportTitle = "SGPA Report for "
                        + (semester != null ? semester.getDisplayValue() : "Selected Semester");

                break;

            case CGPA_OVERALL:

                generatedReportTitle = "Overall CGPA Report";

                break;

            case DAILY_BUY:

                generatedReportTitle = "Daily Buy Report - " + LocalDate.now();

                break;

            case REGISTRATION_FEE_SEMESTER:

                generatedReportTitle = "Registration Fee Report for "
                        + (semester != null ? semester.getDisplayValue() : "Selected Semester");

                break;

            case REGISTRATION_FEE_OVERALL:

                generatedReportTitle = "Overall Registration Fee Report";

                break;

            case COURSE_LIST:

                generatedReportTitle = "Course List Report";

                break;
            case EXPENSE_REPORT:
                generatedReportTitle = "Expense Report - " + LocalDate.now();
                break;
            case ACTION_LOG:
                generatedReportTitle = "Action Log Report - " + LocalDate.now();
                break;
            case NAVIGATION_FLOW:
                generatedReportTitle = "Navigation Flow Report - " + LocalDate.now();
                break;
            case FUND_REPORT:
                generatedReportTitle = "Money Manager Report - " + LocalDate.now();
                break;
            case DATA_JSON_VIEW:
                generatedReportTitle = "Data.json Content View - " + LocalDate.now();
                break;

        }

        // Add space above SHREB heading

        document.add(new Paragraph("\n"));

        // Add SHREB heading

        document.add(new Paragraph("SHREB").setFontSize(14).setBold().setFontColor(ColorConstants.RED).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("\n")); // Add space after SHREB heading

        // Create a table for student details to align colons and apply different colors

        Table studentDetailsTable = new Table(UnitValue.createPercentArray(new float[] { 25, 2, 73 }))

                .useAllAvailableWidth().setBorder(Border.NO_BORDER);

        // Helper to add a row for student details

        BiConsumer<String, String> addDetailRow = (label, value) -> {

            studentDetailsTable.addCell(

                    new Cell().add(new Paragraph(label).setFont(helveticaFont).setFontSize(14).setBold().setFontColor(ColorConstants.BLUE))

                            .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            studentDetailsTable.addCell(

                    new Cell()
                            .add(new Paragraph(":").setFont(helveticaFont).setFontSize(14).setBold()
                                    .setFontColor(ColorConstants.BLUE))

                            .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            studentDetailsTable.addCell(

                    new Cell().add(new Paragraph(" " + value).setFont(helveticaFont).setFontSize(12).setBold().setFontColor(ColorConstants.BLACK))

                            .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        };

        addDetailRow.accept("Student Name", (studentName != null && !studentName.isEmpty() ? studentName : "N/A"));

        addDetailRow.accept("Faculty", (faculty != null && !faculty.isEmpty() ? faculty : "N/A"));

        addDetailRow.accept("Student ID", (studentId != null && !studentId.isEmpty() ? studentId : "N/A"));

        addDetailRow.accept("Registration No",

                (registrationNumber != null && !registrationNumber.isEmpty() ? registrationNumber : "N/A"));

        addDetailRow.accept("Session", (session != null && !session.isEmpty() ? session : "N/A"));

        document.add(studentDetailsTable);

        document.add(new Paragraph("\n").setFont(helveticaFont)); // Add some space after the header table

        // Add signature image and text, right-aligned, below student details
        // Using ImageManager to get the signature path
        String actualSignatureImagePath = utils.ImageManager.getSignatureImagePath();
        if (actualSignatureImagePath != null && !actualSignatureImagePath.isEmpty()) {
            try {
                // Two blank lines for spacing
                document.add(new Paragraph("\n").setFont(helveticaFont));
                document.add(new Paragraph("\n").setFont(helveticaFont));
                Image signature = new Image(ImageDataFactory.create(actualSignatureImagePath));
                signature.scaleToFit(100, 50); // Scale image to fit
                signature.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                signature.setMarginRight(50); // Move a little to the left
                document.add(signature);
                Paragraph signatureText = new Paragraph("Signature").setFont(helveticaFont).setFontSize(12)
                        .setTextAlignment(TextAlignment.RIGHT);
                signatureText.setMarginRight(65); // Shift half the extra left amount back to the right
                document.add(signatureText);
            } catch (Exception e) {
                System.err.println("Could not add signature image to header from ImageManager: " + e.getMessage());
                document.add(new Paragraph("Signature (Image Error: " + e.getMessage() + ")").setFont(helveticaFont)
                        .setFontSize(12)
                        .setTextAlignment(TextAlignment.RIGHT).setMarginRight(65)); // Fallback text
            }
        } else {
            document.add(new Paragraph("No Signature Provided (Path from ImageManager was null/empty)")
                    .setFont(helveticaFont).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginRight(65)); // Fallback if no image path
        }

        document.add(new Paragraph("\n").setFont(helveticaFont)); // Add some space after the signature block

        // Add report type description after session, in a different color

        document.add(
                new Paragraph("Report Type: " + generatedReportTitle).setFont(helveticaFont).setFontSize(14).setBold()

        /* .setFontColor(ColorConstants.GRAY) */);

        document.add(new Paragraph("\n").setFont(helveticaFont)); // Add some space after report type

        // First page only prints user details and report type. Main content starts on
        // next page.

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

    }

    // Helper method to add the specific report content to the document

    private static void _addReportContent(Document document, DataStorage dataStorage, ReportType reportType, 
            Semester semester, List<Task> tasksToReport, PdfFont helveticaFont) {

        // --- Report Generation Logic ---

        switch (reportType) {

            case COMPREHENSIVE:
                generateComprehensiveUniversityReport(document, dataStorage, helveticaFont);
                break;

            case ATTENDANCE_CALENDAR_SEMESTER:

                generateAttendanceCalendar(document, dataStorage, semester, new AttendanceService(dataStorage, null), helveticaFont); // Pass
                                                                                                                       // attendanceService

                break;

            case TASK_LIST_ALL:

            case TASK_LIST_PENDING:

            case TASK_LIST_COMPLETED:

            case TASK_LIST_INCOMPLETE:

                generateTaskReport(document, tasksToReport, reportType, helveticaFont);

                break;

            case CGPA_SEMESTER:

                generateCgpaReport(document, dataStorage, semester,
                        new CgpaService(dataStorage, null, new AttendanceService(dataStorage, null)), helveticaFont);

                break;

            case CGPA_OVERALL:

                generateOverallCgpaReport(document, dataStorage,
                        new CgpaService(dataStorage, null, new AttendanceService(dataStorage, null)), helveticaFont);

                break;

            case DAILY_BUY:

                generateDailyBuyReport(document, dataStorage, dataStorage.getDailyBuys(), helveticaFont);

                break;

            case REGISTRATION_FEE_SEMESTER:

                generateRegistrationFeeReport(document, dataStorage, semester, helveticaFont);

                break;

            case REGISTRATION_FEE_OVERALL:

                generateOverallRegistrationFeeReport(document, dataStorage, helveticaFont);

                break;

            case COURSE_LIST:

                generateCourseListReport(document, dataStorage, helveticaFont);

                break;
            case EXPENSE_REPORT:
                generateExpenseReport(document, dataStorage, helveticaFont);
                break;
            case ACTION_LOG:
                generateActionLogReport(document, dataStorage.getActionHistory(), helveticaFont);
                break;
            case NAVIGATION_FLOW:
                generateNavigationFlowReport(document, dataStorage.getNavigationHistory(), helveticaFont);
                break;
            case FUND_REPORT:
                generateFundReport(document, dataStorage, helveticaFont);
                break;
            case DATA_JSON_VIEW:
                generateDataJsonReport(document, helveticaFont);
                break;
        }
    }
    
    private static void generateExpenseReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        List<DailyBuy> allBuys = dataStorage.getDailyBuys().stream()
                .sorted(Comparator.comparing(DailyBuy::getDate, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(DailyBuy::getTime, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        if (allBuys.isEmpty()) {
            document.add(new Paragraph("\nNo expenses to display.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        Table expenseTable = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2, 2})).useAllAvailableWidth();
        expenseTable.addHeaderCell(createTableHeaderCell("Item Name", helveticaFont));
        expenseTable.addHeaderCell(createTableHeaderCell("Price (BDT)", helveticaFont));
        expenseTable.addHeaderCell(createTableHeaderCell("Date", helveticaFont));
        expenseTable.addHeaderCell(createTableHeaderCell("Time", helveticaFont));
        expenseTable.addHeaderCell(createTableHeaderCell("Status", helveticaFont));

        double totalPending = 0;
        double totalReceived = 0;

        for (DailyBuy buy : allBuys) {
            expenseTable.addCell(createTableCell(buy.getItemName(), helveticaFont));
            expenseTable.addCell(createTableCell(String.format("%.2f", buy.getPrice()), helveticaFont));
            expenseTable.addCell(createTableCell(buy.getDate() != null ? buy.getDate().toString() : "N/A", helveticaFont));
            expenseTable.addCell(createTableCell(buy.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")), helveticaFont));
            expenseTable.addCell(createTableCell(buy.getStatus(), helveticaFont));

            if ("Pending".equalsIgnoreCase(buy.getStatus())) {
                totalPending += buy.getPrice();
            } else if ("Received".equalsIgnoreCase(buy.getStatus())) {
                totalReceived += buy.getPrice();
            }
        }

        document.add(expenseTable);

        double grandTotal = totalPending + totalReceived;

        document.add(new Paragraph("\nSummary:").setBold().setFont(helveticaFont).setFontSize(14));
        document.add(new Paragraph(String.format("Total Pending: %.2f BDT", totalPending)).setFont(helveticaFont).setFontSize(12));
        document.add(new Paragraph(String.format("Total Received: %.2f BDT", totalReceived)).setFont(helveticaFont).setFontSize(12));
        document.add(new Paragraph(String.format("Grand Total: %.2f BDT", grandTotal)).setBold().setFont(helveticaFont).setFontSize(12).setUnderline());
    }

    // New method to generate a comprehensive university report section
    private static void generateComprehensiveUniversityReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        // Get all configured semesters - Reversed for newest first
        List<Semester> configuredSemesters = dataStorage.getSemesterConfigurations().keySet().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        AttendanceService attendanceService = new AttendanceService(dataStorage, null);
        CgpaService cgpaService = new CgpaService(dataStorage, null, attendanceService);

        for (Semester semester : configuredSemesters) {
            SemesterConfig config = dataStorage.getSemesterConfigurations().get(semester);
            if (config == null || !config.isEntered()) continue;

            // Semester Header
            document.add(new Paragraph("\n" + semester.getDisplayValue()).setBold().setFont(helveticaFont).setFontSize(18)
                    .setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Duration: " + config.getStartDate() + " to " + config.getEndDate())
                    .setFont(helveticaFont).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
            
            // 1. Course List for this semester
            document.add(new Paragraph("\n--- Course List & Performance ---").setBold().setFont(helveticaFont).setFontSize(14));
            _addCourseListTable(document, semester, config, helveticaFont);

            // 2. Attendance Summary for this semester
            List<ClassAttendance> semAttendance = dataStorage.getClassAttendances().stream()
                    .filter(att -> semester.equals(att.getSemester()))
                    .collect(Collectors.toList());
            if (!semAttendance.isEmpty()) {
                _addAttendanceSummaryTable(document, semAttendance, semester.getDisplayValue(), helveticaFont);
            }

            // 3. Attendance Calendar for this semester
            document.add(new Paragraph("\n--- Attendance Calendar ---").setBold().setFont(helveticaFont).setFontSize(14));
            generateAttendanceCalendar(document, dataStorage, semester, attendanceService, helveticaFont);

            // 4. SGPA Report for this semester
            document.add(new Paragraph("\n--- SGPA Calculation ---").setBold().setFont(helveticaFont).setFontSize(14));
            generateCgpaReport(document, dataStorage, semester, cgpaService, helveticaFont);

            // 5. Registration Fees for this semester
            if (dataStorage.getRegistrationFees().containsKey(semester) && !dataStorage.getRegistrationFees().get(semester).isEmpty()) {
                document.add(new Paragraph("\n--- Registration Fees ---").setBold().setFont(helveticaFont).setFontSize(14));
                generateRegistrationFeeReport(document, dataStorage, semester, helveticaFont);
            }

            // 6. Detailed Attendance Records for this semester
            if (!semAttendance.isEmpty()) {
                document.add(new Paragraph("\n--- Detailed Class Logs ---").setBold().setFont(helveticaFont).setFontSize(14));
                _addDetailedAttendanceTable(document, semester.getDisplayValue(), semAttendance, helveticaFont);
            }

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }

        // --- FINAL OVERALL SUMMARY ---
        document.add(new Paragraph("\nOVERALL ACADEMIC SUMMARY").setBold().setFont(helveticaFont).setFontSize(18)
                .setFontColor(ColorConstants.RED).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("\nOverall Performance:").setBold().setFont(helveticaFont).setFontSize(14));
        generateOverallCgpaReport(document, dataStorage, cgpaService, helveticaFont);

        if (!dataStorage.getRegistrationFees().isEmpty()) {
            document.add(new Paragraph("\nOverall Financial Summary (Fees):").setBold().setFont(helveticaFont).setFontSize(14));
            generateOverallRegistrationFeeReport(document, dataStorage, helveticaFont);
        }

        // General Attendance (Records not assigned to any specific semester)
        List<ClassAttendance> generalAttn = dataStorage.getClassAttendances().stream()
                .filter(att -> att.getSemester() == null)
                .collect(Collectors.toList());
        if (!generalAttn.isEmpty()) {
            document.add(new Paragraph("\n--- General Attendance (No Semester Assigned) ---").setBold().setFont(helveticaFont).setFontSize(14));
            _addAttendanceSummaryTable(document, generalAttn, "General", helveticaFont);
            _addDetailedAttendanceTable(document, "General", generalAttn, helveticaFont);
        }
    }

    private static void generateAttendanceCalendar(Document document, DataStorage dataStorage, Semester semester, 
            AttendanceService attendanceService, PdfFont helveticaFont) {
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            document.add(new Paragraph(
                    "\nNo courses configured for " + semester.getDisplayValue() + ". Cannot generate attendance calendar.")
                    .setFont(helveticaFont).setFontSize(12));
            return;
        }

        List<String> semesterCourseCodes = semesterConfig.getCourses().stream() 
                .map(Course::getCourseCode)
                .collect(Collectors.toList());

        Map<YearMonth, List<ClassAttendance>> attendanceByMonth = dataStorage.getClassAttendances().stream()
                .filter(att -> att.getSemester().equals(semester) && semesterCourseCodes.contains(att.getCourseCode()))
                .collect(Collectors.groupingBy(att -> YearMonth.from(att.getDate()), Collectors.toList()));

        List<YearMonth> sortedMonths = attendanceByMonth.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (YearMonth yearMonth : sortedMonths) {
            document.add(new Paragraph("\nAttendance for " + yearMonth.getMonth().name() + " " + yearMonth.getYear() 
                    + " (" + semester.getDisplayValue() + ")")
                    .setBold().setFont(helveticaFont).setFontSize(14));

            Table calendarTable = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
            calendarTable.addHeaderCell(createCalendarHeaderCell("Mon", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Tue", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Wed", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Thu", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Fri", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Sat", helveticaFont));
            calendarTable.addHeaderCell(createCalendarHeaderCell("Sun", helveticaFont));

            LocalDate firstDayOfMonth = yearMonth.atDay(1);
            int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
            int daysInMonth = yearMonth.lengthOfMonth();

            for (int i = 1; i < firstDayOfWeek; i++) {
                calendarTable.addCell(new Cell().add(new Paragraph("").setFont(helveticaFont)));
            }

            for (int day = 1; day <= daysInMonth; day++) {
                LocalDate currentDate = yearMonth.atDay(day);
                List<ClassAttendance> dayAttendances = attendanceByMonth.getOrDefault(yearMonth, new ArrayList<>()) 
                        .stream()
                        .filter(att -> att.getDate().isEqual(currentDate))
                        .sorted(Comparator.comparing(ClassAttendance::getTime, Comparator.nullsLast(Comparator.naturalOrder()))) // Ensure chronological order
                        .collect(Collectors.toList());

                Cell dayCell = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);
                dayCell.add(new Paragraph(String.valueOf(day)).setBold().setFont(helveticaFont).setTextAlignment(TextAlignment.LEFT));

                if (!dayAttendances.isEmpty()) {
                    for (ClassAttendance record : dayAttendances) { // Iterate directly over sorted records
                        String courseCode = record.getCourseCode();
                        String teacherName = record.getTeacherName();
                        long signatureCount = record.getAttendanceSignatures();

                        // String alphanumericPart = courseCode.split("-")[0];
                        // String numericPart = courseCode.split("-")[1];
                        // char lastDigit = numericPart.charAt(numericPart.length() - 1);
                        // String displayCode = alphanumericPart + lastDigit;
                        
                        Paragraph line = new Paragraph().setFontSize(10);
                        line.add(new Text(courseCode + " - " + teacherName + " "));

                        if (record.isPresent()) {
                            String pStr = (signatureCount > 1) ? signatureCount + "P" : "P";
                            line.add(new Text(pStr).setFontColor(ColorConstants.GREEN));
                        } else {
                            line.add(new Text("A").setFontColor(ColorConstants.RED));
                        }
                        
                        if (record.isAttendanceTaken()) {
                             line.add(new Text(" [" + signatureCount + "]").setFontColor(ColorConstants.BLUE));
                        }

                        if (record.isExtraClass()) {
                             line.add(new Text(" [EC]").setFontColor(ColorConstants.MAGENTA));
                        }
                        
                        dayCell.add(line);
                    }
                }
                calendarTable.addCell(dayCell);
            }

            int totalCellsFilled = (firstDayOfWeek - 1) + daysInMonth;
            int remainingCells = (7 - (totalCellsFilled % 7)) % 7;
            for (int i = 0; i < remainingCells; i++) {
                calendarTable.addCell(new Cell().add(new Paragraph("").setFont(helveticaFont)));
            }
            document.add(calendarTable);
        }

        // --- Attendance Summary per Course and Instructor ---
        document.add(new Paragraph("\nAttendance Summary").setBold().setFont(helveticaFont).setFontSize(14));
        Table summaryTable = new Table(UnitValue.createPercentArray(new float[] { 3, 4, 3, 3, 3 })).useAllAvailableWidth();
        summaryTable.addHeaderCell(createAttendanceSummaryTableHeaderCell("Course Code", helveticaFont))
                .addHeaderCell(createAttendanceSummaryTableHeaderCell("Instructor", helveticaFont))
                .addHeaderCell(createAttendanceSummaryTableHeaderCell("Present / Total", helveticaFont))
                .addHeaderCell(createAttendanceSummaryTableHeaderCell("Extra Class", helveticaFont))
                .addHeaderCell(createAttendanceSummaryTableHeaderCell("Signatures", helveticaFont));

        Map<String, Map<String, List<ClassAttendance>>> attendanceByCourseAndInstructor = dataStorage
                .getClassAttendances().stream()
                .filter(att -> att.getSemester().equals(semester) && semesterCourseCodes.contains(att.getCourseCode()))
                .collect(Collectors.groupingBy(ClassAttendance::getCourseCode,
                        Collectors.groupingBy(ClassAttendance::getTeacherName)));

        long grandTotalPresent = 0;
        long grandTotalClasses = 0;
        long grandTotalEcTaken = 0;
        long grandTotalSignatures = 0;

        for (Map.Entry<String, Map<String, List<ClassAttendance>>> courseEntry : attendanceByCourseAndInstructor
                .entrySet()) {
            String courseCode = courseEntry.getKey();
            for (Map.Entry<String, List<ClassAttendance>> instructorEntry : courseEntry.getValue().entrySet()) {
                String instructorName = instructorEntry.getKey();
                List<ClassAttendance> records = instructorEntry.getValue();
                long presentCount = records.stream().filter(ClassAttendance::isPresent).count();
                long totalCount = records.size();
                long ecTakenCount = records.stream().filter(ClassAttendance::isAttendanceTaken).count();
                long totalSignatures = records.stream().mapToLong(ClassAttendance::getAttendanceSignatures).sum();

                summaryTable.addCell(createTableCell(courseCode, helveticaFont));
                summaryTable.addCell(createTableCell(instructorName, helveticaFont));
                summaryTable.addCell(createTableCell(presentCount + " / " + totalCount, helveticaFont));
                summaryTable.addCell(createTableCell(String.valueOf(ecTakenCount), helveticaFont));
                summaryTable.addCell(createTableCell(String.valueOf(totalSignatures), helveticaFont));

                grandTotalPresent += presentCount;
                grandTotalClasses += totalCount;
                grandTotalEcTaken += ecTakenCount;
                grandTotalSignatures += totalSignatures;
            }
        }
        document.add(summaryTable);

        // --- Grand Total ---
        Paragraph grandTotalPara = new Paragraph()
            .add(new Text("Grand Total: ").setBold())
            .add("Present: " + grandTotalPresent + " | Total: " + grandTotalClasses)
            .add(" | Extra Class: " + grandTotalEcTaken)
            .add(" | Signatures: " + grandTotalSignatures)
            .setFont(helveticaFont).setFontSize(14).setUnderline();
        document.add(grandTotalPara);
    }

    // Helper for table header cells (re-using existing ones, or creating new if
    // needed)
    private static Cell createTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)/*
                                                                                   * .setFontColor(ColorConstants.WHITE)
                                                                                   */.setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }

    // Helper for table data cells
    private static Cell createTableCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setFont(helveticaFont).setFontSize(12))
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static void generateTaskReport(Document document, List<Task> tasks, ReportType reportType, PdfFont helveticaFont) {
        if (tasks == null || tasks.isEmpty()) {
            document.add(
                    new Paragraph("No tasks to display for this report type.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        // Filter tasks based on reportType if not ALL
        List<Task> filteredTasks = new ArrayList<>();
        if (reportType == ReportType.TASK_LIST_ALL) {
            filteredTasks = new ArrayList<>(tasks); // Create a copy
        } else if (reportType == ReportType.TASK_LIST_PENDING) {
            filteredTasks = tasks.stream().filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
        } else if (reportType == ReportType.TASK_LIST_COMPLETED) {
            filteredTasks = tasks.stream().filter(t -> t.getStatus() == TaskStatus.COMPLETED)
                    .collect(Collectors.toList());
        } else if (reportType == ReportType.TASK_LIST_INCOMPLETE) { // Added for completeness
            filteredTasks = tasks.stream().filter(t -> t.getStatus() == TaskStatus.INCOMPLETE)
                    .collect(Collectors.toList());
        }

        if (filteredTasks.isEmpty()) {
            document.add(new Paragraph("No tasks found matching criteria for this report type.").setFont(helveticaFont)
                    .setFontSize(12));
            return;
        }

        Table taskTable = new Table(UnitValue.createPercentArray(new float[] { 2, 2, 2, 3, 3, 2 }))
                .useAllAvailableWidth();
        taskTable.addHeaderCell(createTaskTableHeaderCell("Category", helveticaFont));
        taskTable.addHeaderCell(createTaskTableHeaderCell("Task Name", helveticaFont));
        taskTable.addHeaderCell(createTaskTableHeaderCell("Status", helveticaFont));
        taskTable.addHeaderCell(createTaskTableHeaderCell("Date", helveticaFont));
        taskTable.addHeaderCell(createTaskTableHeaderCell("Start Time", helveticaFont));
        taskTable.addHeaderCell(createTaskTableHeaderCell("End Time", helveticaFont));

        // Sort tasks for consistent reporting, handling null dates and times
        filteredTasks.sort(Comparator.comparing(Task::getDate, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Task::getStartTime, Comparator.nullsLast(Comparator.naturalOrder())));

        for (Task task : filteredTasks) {
            String statusText = "";
            switch (task.getStatus()) {
                case PENDING:
                    statusText = "PENDING";
                    break;
                case COMPLETED:
                    statusText = "COMPLETED";
                    break;
                case INCOMPLETE:
                    statusText = "INCOMPLETE";
                    break;
            }

            taskTable.addCell(createTaskTableCell(task.getCategory() != null ? task.getCategory().name() : "N/A", helveticaFont));
            taskTable.addCell(createTaskTableCell(task.getTaskName() != null ? task.getTaskName() : "N/A", helveticaFont));
            taskTable.addCell(createTaskTableCell(statusText, helveticaFont));
            taskTable.addCell(createTaskTableCell(task.getDate() != null ? task.getDate().toString() : "N/A", helveticaFont));
            taskTable
                    .addCell(createTaskTableCell(task.getStartTime() != null ? task.getStartTime().toString() : "N/A", helveticaFont));
            taskTable.addCell(createTaskTableCell(task.getEndTime() != null ? task.getEndTime().toString() : "N/A", helveticaFont));
        }
        document.add(taskTable);
    }

    private static Cell createTaskTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)/*
                                                                                   * .setFontColor(ColorConstants.WHITE)
                                                                                   */.setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createTaskTableCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setFont(helveticaFont).setFontSize(12))
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static void generateCgpaReport(Document document, DataStorage dataStorage, Semester semester,
            CgpaService cgpaService, PdfFont helveticaFont) {

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            document.add(new Paragraph(
                    "\nNo courses configured for " + semester.getDisplayValue() + ". Cannot generate SGPA report.")
                    .setFont(helveticaFont).setFontSize(12));
            return;
        }
        
        // Get extra class counts
        Map<String, Long> extraClassCounts = dataStorage.getClassAttendances().stream()
                .filter(ca -> ca.isExtraClass() && ca.getSemester().equals(semester))
                .collect(Collectors.groupingBy(ClassAttendance::getCourseCode, Collectors.counting()));

        // Ensure courses are up-to-date with attendance and marks
        cgpaService.calculateSGPA(semester);

        List<Course> courses = semesterConfig.getCourses();

        double sgpa = cgpaService.calculateSGPA(semester);

        Table cgpaTable = new Table(UnitValue.createPercentArray(new float[] { 2, 4, 1, 1, 1, 1, 1, 1, 2, 2 }))
                .useAllAvailableWidth();
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("Code", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("Title", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("CRD", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("M", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("A", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("P", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("F", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("TOT", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("Grade", helveticaFont));
        cgpaTable.addHeaderCell(createCgpaTableHeaderCell("GP", helveticaFont));

        for (Course course : courses) {
            String courseTitle = course.getCourseTitle();
            long extraClasses = extraClassCounts.getOrDefault(course.getCourseCode(), 0L);
            if (extraClasses > 0) {
                StringBuilder pluses = new StringBuilder();
                for (int i = 0; i < extraClasses; i++) {
                    pluses.append("+");
                }
                courseTitle += " " + pluses.toString();
            }

            cgpaTable.addCell(createCgpaTableCell(course.getCourseCode(), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(courseTitle, helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.valueOf(course.getCreditHours()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.valueOf(course.getMidMarks()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.valueOf(course.getAssignmentMarks()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.valueOf(course.getAttendanceMarks()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.valueOf(course.getFinalMarks()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.format("%.2f", course.getTotalMarks()), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(course.getLetterGrade(), helveticaFont));
            cgpaTable.addCell(createCgpaTableCell(String.format("%.2f", course.getGradePoint()), helveticaFont));
        }
        document.add(cgpaTable);
        // Add SGPA total line
        document.add(
                new Paragraph("Total SGPA: " + String.format("%.2f", sgpa)).setBold().setFont(helveticaFont)
                        .setFontSize(12).setUnderline());
    }

    private static Cell createCgpaTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)/*
                                                                                   * .setFontColor(ColorConstants.WHITE)
                                                                                   */.setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createCgpaTableCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setFont(helveticaFont).setFontSize(12))
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static void generateOverallCgpaReport(Document document, DataStorage dataStorage, CgpaService cgpaService, PdfFont helveticaFont) {
        OverallCgpaResult overallCgpaResult = cgpaService.calculateOverallCgpa();

        if (overallCgpaResult.overallCgpa() == 0.0) {
            document.add(new Paragraph("\nNo overall CGPA data available.").setFont(helveticaFont).setFontSize(12));
        } else {
            document.add(new Paragraph("\nOverall CGPA: " + String.format("%.2f", overallCgpaResult.overallCgpa())
                    + " (Total Credit Hours: " + String.format("%.1f", overallCgpaResult.totalCreditHours()) + ")")
                    .setBold().setFont(helveticaFont).setFontSize(14).setUnderline());
        }
    }

    private static void generateDailyBuyReport(Document document, DataStorage dataStorage, 
            List<DailyBuy> buysToReport, PdfFont helveticaFont) {
        if (buysToReport == null || buysToReport.isEmpty()) {
            document.add(new Paragraph("\nNo daily buy records available for this report.").setFont(helveticaFont)
                    .setFontSize(12));
            return;
        }

        // Sort by date (descending) and time (descending)
        List<DailyBuy> sortedBuys = buysToReport.stream()
                .sorted(Comparator.comparing(DailyBuy::getDate, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(DailyBuy::getTime, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());

        Table buyTable = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2})).useAllAvailableWidth();
        
        // Header with colors
        buyTable.addHeaderCell(new Cell().add(new Paragraph("Item Name").setBold().setFont(helveticaFont).setFontSize(12))
                .setBackgroundColor(ColorConstants.CYAN)
                .setTextAlignment(TextAlignment.CENTER));
        buyTable.addHeaderCell(new Cell().add(new Paragraph("Price (BDT)").setBold().setFont(helveticaFont).setFontSize(12))
                .setBackgroundColor(ColorConstants.YELLOW)
                .setTextAlignment(TextAlignment.CENTER));
        buyTable.addHeaderCell(new Cell().add(new Paragraph("Date").setBold().setFont(helveticaFont).setFontSize(12))
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));
        buyTable.addHeaderCell(new Cell().add(new Paragraph("Time").setBold().setFont(helveticaFont).setFontSize(12))
                .setBackgroundColor(ColorConstants.PINK)
                .setTextAlignment(TextAlignment.CENTER));

        double totalExpenditure = 0.0;
        for (DailyBuy buy : sortedBuys) {
            buyTable.addCell(createTableCell(buy.getItemName(), helveticaFont));
            buyTable.addCell(createTableCell(String.format("%.2f", buy.getPrice()), helveticaFont).setTextAlignment(TextAlignment.RIGHT));
            buyTable.addCell(createTableCell(buy.getDate() != null ? buy.getDate().toString() : "N/A", helveticaFont));
            buyTable.addCell(createTableCell(buy.getTime() != null ? Utils.formatTime(buy.getTime()) : "N/A", helveticaFont));
            totalExpenditure += buy.getPrice();
        }

        document.add(buyTable);

        document.add(new Paragraph("\nTotal Expenditure: " + String.format("%.2f", totalExpenditure) + " BDT")
                .setBold().setFont(helveticaFont).setFontSize(14).setFontColor(ColorConstants.RED).setTextAlignment(TextAlignment.RIGHT));
    }

    private static void generateRegistrationFeeReport(Document document, DataStorage dataStorage, Semester semester, PdfFont helveticaFont) {
        RegistrationFeeService registrationFeeService = new RegistrationFeeService(dataStorage, null); // DataManager
                                                                                                       // not needed
                                                                                                       // here
        List<RegistrationFee> fees = registrationFeeService.getRegistrationFeesForSemester(semester);

        if (fees.isEmpty()) {
            document.add(new Paragraph("\nNo registration fees available for " + semester.getDisplayValue() + ".")
                    .setFont(helveticaFont).setFontSize(12));
            return;
        }

        Table feeTable = new Table(UnitValue.createPercentArray(new float[] { 3, 5, 2 })).useAllAvailableWidth();
        feeTable.addHeaderCell(createFeeTableHeaderCell("Fee Type", helveticaFont));
        feeTable.addHeaderCell(createFeeTableHeaderCell("Description", helveticaFont));
        feeTable.addHeaderCell(createFeeTableHeaderCell("Amount (BDT)", helveticaFont));

        for (RegistrationFee fee : fees) {
            feeTable.addCell(createFeeTableCell(fee.getFeeType().name(), helveticaFont));
            feeTable.addCell(createFeeTableCell(fee.getDescription(), helveticaFont));
            feeTable.addCell(createFeeTableCell(String.format("%.2f", fee.getAmount()), helveticaFont));
        }
        document.add(feeTable);

        double totalFees = registrationFeeService.calculateTotalFeesForSemester(semester);
        document.add(new Paragraph(
                "Total Fees for " + semester.getDisplayValue() + ": " + String.format("%.2f", totalFees) + " BDT")
                .setBold().setFont(helveticaFont).setFontSize(12).setUnderline());
    }

    private static void generateOverallRegistrationFeeReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        RegistrationFeeService registrationFeeService = new RegistrationFeeService(dataStorage, null); // DataManager
                                                                                                       // not needed
                                                                                                       // here

        if (dataStorage.getRegistrationFees().isEmpty()) {
            document.add(new Paragraph("No registration fee data available for any semester.").setFont(helveticaFont)
                    .setFontSize(12));
        } else {
            dataStorage.getRegistrationFees().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey()) // Sort by semester
                    .forEach(entry -> {
                        Semester semester = entry.getKey();
                        try {
                            generateRegistrationFeeReport(document, dataStorage, semester, helveticaFont); // Use the existing method
                                                                                            // for per-semester details
                            document.add(new Paragraph("\n").setFont(helveticaFont)); // Add space between semester
                                                                                      // reports
                        } catch (Exception e) {
                            document.add(new Paragraph("Error generating registration fee report for "
                                    + semester.getDisplayValue() + ": " + e.getMessage()).setFont(helveticaFont)
                                    .setFontSize(8)
                            /* .setFontColor(ColorConstants.RED) */);
                        }
                    });

            double overallTotalFees = registrationFeeService.calculateOverallTotalFees();
            document.add(new Paragraph(
                    "Overall Total Registration Fees: " + String.format("%.2f", overallTotalFees) + " BDT").setBold() 
                    .setFont(helveticaFont).setFontSize(14).setUnderline());
        }
    }

    private static Cell createFeeTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)/*
                                                                                   * .setFontColor(ColorConstants.WHITE)
                                                                                   */.setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createFeeTableCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setFont(helveticaFont).setFontSize(12))
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static Cell createCalendarHeaderCell(String dayName, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(dayName).setBold().setFont(helveticaFont)/*
                                                                                      * .setFontColor(ColorConstants.
                                                                                      * BLUE)
                                                                                      */.setFontSize(12))
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(ColorConstants.YELLOW);
    }

    private static void generateCourseListReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        if (dataStorage.getSemesterConfigurations().isEmpty()) {
            document.add(new Paragraph("No courses configured.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        dataStorage.getSemesterConfigurations().entrySet().stream()
                .sorted(Map.Entry.<Semester, SemesterConfig>comparingByKey().reversed())
                .forEach(entry -> {
                    Semester semester = entry.getKey();
                    SemesterConfig config = entry.getValue();
                    if (config.isEntered() && !config.getCourses().isEmpty()) {
                        document.add(new Paragraph("\n" + semester.getDisplayValue()).setBold().setFont(helveticaFont)
                                .setFontSize(14));
                        _addCourseListTable(document, semester, config, helveticaFont);
                    }
                });
    }

    private static void _addCourseListTable(Document document, Semester semester, SemesterConfig config, PdfFont helveticaFont) {
        Table courseTable = new Table(UnitValue.createPercentArray(new float[] { 3, 7, 2, 2, 2, 2, 2, 2 }))
                .useAllAvailableWidth();
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Code", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Course Title", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Cr", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Mid", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Final", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Asgn", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Attn", helveticaFont));
        courseTable.addHeaderCell(createCourseListTableHeaderCell("Total", helveticaFont));

        for (Course course : config.getCourses()) {
            courseTable.addCell(createCourseListTableCell(course.getCourseCode(), helveticaFont));
            courseTable.addCell(createCourseListTableCell(course.getCourseTitle(), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getCreditHours()), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getMidMarks()), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getFinalMarks()), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getAssignmentMarks()), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getAttendanceMarks()), helveticaFont));
            courseTable.addCell(createCourseListTableCell(String.format("%.1f", course.getTotalMarks()), helveticaFont));
        }
        document.add(courseTable);
    }

    private static void generateDetailedAttendanceReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        List<ClassAttendance> allAttendance = dataStorage.getClassAttendances();
        if (allAttendance.isEmpty()) {
            document.add(new Paragraph("No attendance records found.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        Map<String, List<ClassAttendance>> groupedBySemester = allAttendance.stream()
                .collect(Collectors.groupingBy(att -> att.getSemester() != null ? att.getSemester().getDisplayValue() : "General Attendance (No Semester)"));

        List<String> sortedSemesterNames = groupedBySemester.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (String semName : sortedSemesterNames) {
            _addDetailedAttendanceTable(document, semName, groupedBySemester.get(semName), helveticaFont);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }
    }

    private static void _addDetailedAttendanceTable(Document document, String semName, List<ClassAttendance> records, PdfFont helveticaFont) {
        document.add(new Paragraph("\nDetailed Attendance: " + semName).setBold().setFont(helveticaFont).setFontSize(14));
        
        Table attnTable = new Table(UnitValue.createPercentArray(new float[] { 2, 2, 3, 4, 1, 1, 4, 4, 1 }))
                .useAllAvailableWidth();
        
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Date", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Time", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Course", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Teacher", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("S", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("O", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Topic", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("Materials", helveticaFont));
        attnTable.addHeaderCell(createCourseListTableHeaderCell("C", helveticaFont));

        List<ClassAttendance> sortedRecords = records.stream()
                .sorted(Comparator.comparing(ClassAttendance::getDate, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(ClassAttendance::getTime, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());

        for (ClassAttendance ca : sortedRecords) {
            attnTable.addCell(createCourseListTableCell(ca.getDate() != null ? ca.getDate().toString() : "N/A", helveticaFont));
            attnTable.addCell(createCourseListTableCell(Utils.formatTime(ca.getTime()), helveticaFont));
            attnTable.addCell(createCourseListTableCell(ca.getCourseCode(), helveticaFont));
            attnTable.addCell(createCourseListTableCell(ca.getTeacherName(), helveticaFont));
            
            String statusStr = ca.isPresent() ? (ca.getAttendanceSignatures() > 1 ? ca.getAttendanceSignatures() + "P" : "P") : "A";
            Cell statusCell = createCourseListTableCell(statusStr, helveticaFont);
            if (ca.isPresent()) statusCell.setFontColor(ColorConstants.GREEN);
            else statusCell.setFontColor(ColorConstants.RED);
            attnTable.addCell(statusCell);
            
            attnTable.addCell(createCourseListTableCell(ca.isOnline() ? "●" : "", helveticaFont));
            attnTable.addCell(createCourseListTableCell(ca.getTopic() != null ? ca.getTopic() : "", helveticaFont));
            
            String mats = (ca.getMaterials() != null && !ca.getMaterials().isEmpty()) ? String.join(", ", ca.getMaterials()) : "";
            attnTable.addCell(createCourseListTableCell(mats, helveticaFont));
            
            attnTable.addCell(createCourseListTableCell(String.valueOf(ca.getAttendanceSignatures()), helveticaFont));
        }
        document.add(attnTable);
        document.add(new Paragraph("S=Status (P/A), O=Online (●), C=Count").setFont(helveticaFont).setFontSize(8).setItalic());
    }

    private static void generateAttendanceSummaryTable(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        _addAttendanceSummaryTable(document, dataStorage.getClassAttendances(), "Overall", helveticaFont);
    }

    private static void _addAttendanceSummaryTable(Document document, List<ClassAttendance> attendanceList, String titleSuffix, PdfFont helveticaFont) {
        if (attendanceList.isEmpty()) {
            return;
        }

        document.add(new Paragraph("\n--- Attendance Statistics Summary (" + titleSuffix + ") ---").setBold().setFont(helveticaFont).setFontSize(14));
        
        Map<String, Map<String, List<ClassAttendance>>> grouped = attendanceList.stream()
                .collect(Collectors.groupingBy(ClassAttendance::getCourseCode, 
                         Collectors.groupingBy(ClassAttendance::getTeacherName)));

        Table summaryTable = new Table(UnitValue.createPercentArray(new float[] { 4, 4, 2, 2, 2, 2 }))
                .useAllAvailableWidth();
        
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("Course", helveticaFont));
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("Teacher", helveticaFont));
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("Total", helveticaFont));
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("P", helveticaFont));
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("A", helveticaFont));
        summaryTable.addHeaderCell(createCourseListTableHeaderCell("%", helveticaFont));

        for (String course : grouped.keySet()) {
            for (String teacher : grouped.get(course).keySet()) {
                List<ClassAttendance> records = grouped.get(course).get(teacher);
                long total = records.size();
                long present = records.stream().filter(ClassAttendance::isPresent).count();
                long absent = total - present;
                double percentage = (total > 0) ? (present * 100.0 / total) : 0.0;

                summaryTable.addCell(createCourseListTableCell(course, helveticaFont));
                summaryTable.addCell(createCourseListTableCell(teacher, helveticaFont));
                summaryTable.addCell(createCourseListTableCell(String.valueOf(total), helveticaFont));
                summaryTable.addCell(createCourseListTableCell(String.valueOf(present), helveticaFont));
                summaryTable.addCell(createCourseListTableCell(String.valueOf(absent), helveticaFont));
                summaryTable.addCell(createCourseListTableCell(String.format("%.1f%%", percentage), helveticaFont));
            }
        }
        document.add(summaryTable);
        document.add(new Paragraph("P=Present, A=Absent").setFont(helveticaFont).setFontSize(8).setItalic());
    }

    private static Cell createCourseListTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)/*
                                                                                   * .setFontColor(ColorConstants.WHITE)
                                                                                   */.setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }


    private static Cell createCourseListTableCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setFont(helveticaFont).setFontSize(12))
                .setTextAlignment(TextAlignment.LEFT);
    }

    private static PdfFont _createFontWithFallback() {
        try {
            return PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException e) {
            System.err.println("Error creating Helvetica font: " + e.getMessage());
            try {
                return PdfFontFactory.createFont(StandardFonts.COURIER);
            } catch (IOException ex) {
                System.err.println("Error creating fallback font: " + ex.getMessage());
                throw new RuntimeException("Failed to load any PDF font.", ex);
            }
        }
    }

    private static void generateActionLogReport(Document document, List<DataStorage.ActionEntry> history, PdfFont helveticaFont) {
        if (history == null || history.isEmpty()) {
            document.add(new Paragraph("\nNo actions have been recorded yet.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        document.add(new Paragraph("\n--- Full Action Log ---").setBold().setFont(helveticaFont).setFontSize(14));
        
        Table actionLogTable = new Table(UnitValue.createPercentArray(new float[]{1, 5, 2, 3})).useAllAvailableWidth();
        
        // Header with multiple colors like console
        actionLogTable.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setFont(helveticaFont))
                .setBackgroundColor(ColorConstants.CYAN).setTextAlignment(TextAlignment.CENTER));
        actionLogTable.addHeaderCell(new Cell().add(new Paragraph("Action Message").setBold().setFont(helveticaFont))
                .setBackgroundColor(ColorConstants.ORANGE).setTextAlignment(TextAlignment.CENTER));
        actionLogTable.addHeaderCell(new Cell().add(new Paragraph("Time").setBold().setFont(helveticaFont))
                .setBackgroundColor(ColorConstants.MAGENTA).setTextAlignment(TextAlignment.CENTER));
        actionLogTable.addHeaderCell(new Cell().add(new Paragraph("Date").setBold().setFont(helveticaFont))
                .setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER));

        for (int i = 0; i < history.size(); i++) {
            DataStorage.ActionEntry entry = history.get(i);
            String timeFormatted = entry.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String dateFormatted = entry.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yy (EEEE)"));

            // Serial Number with alternating colors
            Cell serialCell = new Cell().add(new Paragraph(String.valueOf(i + 1)).setFont(helveticaFont));
            if (i % 2 == 0) serialCell.setFontColor(ColorConstants.CYAN);
            actionLogTable.addCell(serialCell);

            // Action text in Yellow Bold like console
            actionLogTable.addCell(new Cell().add(new Paragraph(entry.getAction()).setBold().setFont(helveticaFont).setFontColor(ColorConstants.DARK_GRAY)));
            
            // Time in Purple Bright like console
            actionLogTable.addCell(new Cell().add(new Paragraph(timeFormatted).setFont(helveticaFont).setFontColor(ColorConstants.MAGENTA)));
            
            // Date in Blue Bright like console
            actionLogTable.addCell(new Cell().add(new Paragraph(dateFormatted).setFont(helveticaFont).setFontColor(ColorConstants.BLUE)));
        }
        document.add(actionLogTable);
    }

    private static void generateDataJsonReport(Document document, PdfFont helveticaFont) {
        String dataFilePath = Config.DATA_PATH;
        java.io.File file = new java.io.File(dataFilePath);

        if (!file.exists()) {
            document.add(new Paragraph("Error: data.json file not found.").setFont(helveticaFont).setFontColor(ColorConstants.RED));
            return;
        }

        PdfFont courierFont;
        try {
            courierFont = PdfFontFactory.createFont(StandardFonts.COURIER);
        } catch (IOException e) {
            courierFont = helveticaFont;
        }

        // High-contrast color mapping for white PDF background
        com.itextpdf.kernel.colors.DeviceRgb jsonKeyDefault = new com.itextpdf.kernel.colors.DeviceRgb(0, 50, 150); // Deep Blue
        com.itextpdf.kernel.colors.DeviceRgb jsonValueColor = new com.itextpdf.kernel.colors.DeviceRgb(0, 120, 0); // Forest Green
        com.itextpdf.kernel.colors.DeviceRgb jsonBracketColor = new com.itextpdf.kernel.colors.DeviceRgb(80, 80, 80); // Dark Gray
        com.itextpdf.kernel.colors.DeviceRgb jsonQuoteColor = new com.itextpdf.kernel.colors.DeviceRgb(100, 100, 100); // Medium Gray
        com.itextpdf.kernel.colors.DeviceRgb jsonLineNumberColor = new com.itextpdf.kernel.colors.DeviceRgb(60, 60, 60); // Very Dark Gray

        // Key-specific colors (Darker variants for visibility)
        java.util.Map<String, com.itextpdf.kernel.colors.Color> keyColors = new java.util.HashMap<>();
        keyColors.put("itemName", new com.itextpdf.kernel.colors.DeviceRgb(0, 80, 200));   // Solid Blue
        keyColors.put("price", new com.itextpdf.kernel.colors.DeviceRgb(0, 100, 0));      // Dark Green
        keyColors.put("date", new com.itextpdf.kernel.colors.DeviceRgb(150, 100, 0));    // Dark Gold/Brownish-Yellow
        keyColors.put("time", new com.itextpdf.kernel.colors.DeviceRgb(120, 0, 120));    // Dark Purple
        keyColors.put("status", new com.itextpdf.kernel.colors.DeviceRgb(200, 50, 0));   // Dark Orange
        keyColors.put("courseCode", new com.itextpdf.kernel.colors.DeviceRgb(180, 0, 0)); // Dark Red
        keyColors.put("courseName", new com.itextpdf.kernel.colors.DeviceRgb(50, 80, 50)); // Deep Olive
        keyColors.put("teacherName", new com.itextpdf.kernel.colors.DeviceRgb(0, 50, 120)); // Navy Blue

        // Darker Line number cycle colors (No light yellows or neons)
        com.itextpdf.kernel.colors.Color[] cycleColors = {
            new com.itextpdf.kernel.colors.DeviceRgb(150, 0, 0),   // Red
            new com.itextpdf.kernel.colors.DeviceRgb(0, 100, 0),   // Green
            new com.itextpdf.kernel.colors.DeviceRgb(0, 0, 150),   // Blue
            new com.itextpdf.kernel.colors.DeviceRgb(120, 80, 0),  // Gold/Brown
            new com.itextpdf.kernel.colors.DeviceRgb(100, 0, 100), // Purple
            new com.itextpdf.kernel.colors.DeviceRgb(0, 100, 100)  // Teal
        };

        try {
            String jsonContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(dataFilePath)));
            String[] lines = jsonContent.split("\n");
            int lineNumber = 1;
            
            for (String line : lines) {
                Paragraph p = new Paragraph().setFont(courierFont).setFontSize(12).setFixedLeading(14f);
                
                // 1. Line Number
                p.add(new Text(String.format("%4d ", lineNumber)).setFontColor(cycleColors[lineNumber % cycleColors.length]));
                lineNumber++;

                // 2. Indentation
                String trimmedLine = line.trim();
                int leadingSpacesCount = line.indexOf(trimmedLine);
                if (leadingSpacesCount > 0) {
                    p.add(new Text("\u00A0".repeat(leadingSpacesCount)));
                }

                // 3. Content parsing (Simple)
                if (trimmedLine.startsWith("\"") && trimmedLine.contains(":")) {
                    int colonIndex = trimmedLine.indexOf(":");
                    String keyPart = trimmedLine.substring(0, colonIndex);
                    String valuePart = trimmedLine.substring(colonIndex);
                    
                    // Key with quotes
                    String actualKey = keyPart.replace("\"", "");
                    p.add(new Text("\"").setFontColor(jsonQuoteColor));
                    p.add(new Text(actualKey).setFontColor(keyColors.getOrDefault(actualKey, jsonKeyDefault)).setBold());
                    p.add(new Text("\"").setFontColor(jsonQuoteColor));
                    
                    // Colon and Value
                    p.add(new Text(": ").setFontColor(jsonQuoteColor));
                    p.add(new Text(valuePart.substring(1).trim()).setFontColor(jsonValueColor));
                } else if (trimmedLine.equals("{") || trimmedLine.equals("}") || trimmedLine.equals("[") || trimmedLine.equals("]") || 
                           trimmedLine.equals("},") || trimmedLine.equals("],")) {
                    p.add(new Text(trimmedLine).setBold().setFontColor(jsonBracketColor));
                } else {
                    p.add(new Text(trimmedLine).setFontColor(ColorConstants.BLACK));
                }
                
                document.add(p);
            }
        } catch (IOException e) {
            document.add(new Paragraph("Error reading data.json: " + e.getMessage()).setFontColor(ColorConstants.RED));
        }
    }

    private static void generateNavigationFlowReport(Document document, List<DataStorage.NavigationEvent> navigationHistory, PdfFont helveticaFont) {
        if (navigationHistory.isEmpty()) {
            document.add(new Paragraph("\nNo navigation history available yet.").setFont(helveticaFont).setFontSize(12));
            return;
        }

        document.add(new Paragraph("\n--- Navigation Flow History ---").setBold().setFont(helveticaFont).setFontSize(14));
        
        PdfFont courierFont;
        try {
            courierFont = PdfFontFactory.createFont(StandardFonts.COURIER);
        } catch (IOException e) {
            courierFont = helveticaFont;
        }

        LocalDate lastDisplayedDate = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (DataStorage.NavigationEvent event : navigationHistory) {
            LocalDate currentDate = event.getTimestamp().toLocalDate();

            if (lastDisplayedDate == null || !currentDate.equals(lastDisplayedDate)) {
                document.add(new Paragraph("\n--- Date: " + currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " ---")
                        .setBold().setFont(helveticaFont).setFontSize(12).setFontColor(ColorConstants.RED));
                lastDisplayedDate = currentDate;
            }

            String arrow = "";
            com.itextpdf.kernel.colors.Color directionColor = ColorConstants.BLACK;
            
            if ("FORWARD".equals(event.getActionType())) {
                arrow = "->";
                directionColor = ColorConstants.GREEN;
            } else if ("BACKWARD".equals(event.getActionType())) {
                arrow = "<-";
                directionColor = ColorConstants.RED;
            } else {
                arrow = "--";
                directionColor = ColorConstants.YELLOW;
            }

            // Using Non-breaking spaces (\u00A0) to ensure PDF preserves indentation
            StringBuilder indentBuilder = new StringBuilder();
            for (int i = 0; i < event.getDepth(); i++) {
                indentBuilder.append("\u00A0\u00A0"); // 2 non-breaking spaces per depth level
            }
            String indent = indentBuilder.toString();

            Paragraph flowEntry = new Paragraph().setFont(courierFont).setFontSize(10).setFixedLeading(12f);
            
            // 1. Add Indent
            flowEntry.add(new Text(indent));
            
            // 2. Add Timestamp in brackets (Gray)
            flowEntry.add(new Text("[" + event.getTimestamp().format(timeFormatter) + "] ").setFontColor(ColorConstants.GRAY));
            
            // 3. Add Menu Name (Bold, Green/Red based on direction)
            flowEntry.add(new Text(event.getMenuName() + " ").setBold().setFontColor(directionColor));
            
            // 4. Add Arrow (Bold, Green/Red based on direction)
            flowEntry.add(new Text(arrow).setBold().setFontColor(directionColor));
            
            document.add(flowEntry);
        }
    }

    private static Cell createAttendanceSummaryTableHeaderCell(String text, PdfFont helveticaFont) {
        return new Cell().add(new Paragraph(text).setBold().setFont(helveticaFont)
                                                                                   .setFontColor(ColorConstants.WHITE)
                                                                                   .setFontSize(12))
                .setBackgroundColor(ColorConstants.GREEN)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static void generateFundReport(Document document, DataStorage dataStorage, PdfFont helveticaFont) {
        List<FundAccount> accounts = dataStorage.getFundAccounts();
        List<FundTransaction> transactions = dataStorage.getFundTransactions();

        // 1. Accounts Summary Table
        document.add(new Paragraph("\nAccounts Summary").setBold().setFont(helveticaFont).setFontSize(14));
        if (accounts.isEmpty()) {
            document.add(new Paragraph("No accounts found.").setFont(helveticaFont).setFontSize(12));
        } else {
            Table accountTable = new Table(UnitValue.createPercentArray(new float[]{3, 3, 4, 3})).useAllAvailableWidth();
            accountTable.addHeaderCell(createTableHeaderCell("Account Name", helveticaFont));
            accountTable.addHeaderCell(createTableHeaderCell("Type", helveticaFont));
            accountTable.addHeaderCell(createTableHeaderCell("Bank/Platform", helveticaFont));
            accountTable.addHeaderCell(createTableHeaderCell("Balance (BDT)", helveticaFont));

            double totalBalance = 0;
            for (FundAccount acc : accounts) {
                accountTable.addCell(createTableCell(acc.getName(), helveticaFont));
                accountTable.addCell(createTableCell(acc.getType().getDisplayName(), helveticaFont));
                accountTable.addCell(createTableCell(acc.getBankName().isEmpty() ? "---" : acc.getBankName(), helveticaFont));
                accountTable.addCell(createTableCell(String.format("%.2f", acc.getBalance()), helveticaFont));
                totalBalance += acc.getBalance();
            }
            document.add(accountTable);
            document.add(new Paragraph("Total Combined Fund: " + String.format("%.2f", totalBalance) + " BDT").setBold().setFont(helveticaFont));
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        // 2. Transaction Records Table
        document.add(new Paragraph("\nTransaction Records").setBold().setFont(helveticaFont).setFontSize(14));
        if (transactions.isEmpty()) {
            document.add(new Paragraph("No transactions recorded.").setFont(helveticaFont).setFontSize(12));
        } else {
            Table txTable = new Table(UnitValue.createPercentArray(new float[]{3, 3, 2, 2, 4})).useAllAvailableWidth();
            txTable.addHeaderCell(createTableHeaderCell("Date & Time", helveticaFont));
            txTable.addHeaderCell(createTableHeaderCell("Account", helveticaFont));
            txTable.addHeaderCell(createTableHeaderCell("Type", helveticaFont));
            txTable.addHeaderCell(createTableHeaderCell("Amount", helveticaFont));
            txTable.addHeaderCell(createTableHeaderCell("Note", helveticaFont));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                FundTransaction tx = transactions.get(i);
                txTable.addCell(createTableCell(tx.getTimestamp().format(formatter), helveticaFont));
                txTable.addCell(createTableCell(tx.getAccountName(), helveticaFont));
                txTable.addCell(createTableCell(tx.getType(), helveticaFont));

                Cell amountCell = createTableCell(String.format("%.2f", tx.getAmount()), helveticaFont);
                if ("ADD".equals(tx.getType())) {
                    amountCell.setFontColor(ColorConstants.GREEN);
                } else {
                    amountCell.setFontColor(ColorConstants.RED);
                }
                txTable.addCell(amountCell);

                txTable.addCell(createTableCell(tx.getNote(), helveticaFont));
            }
            document.add(txTable);
        }
    }
    }