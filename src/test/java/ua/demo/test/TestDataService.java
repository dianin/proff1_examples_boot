package ua.demo.test;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import ua.demo.test.domain.Data;
import ua.demo.test.service.DataService;

import java.io.File;

public class TestDataService extends DBTestCase {

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("C:\\Users\\Игарек\\IdeaProjects\\proff1_examples_boot\\src\\test\\resorces\\TestDataSet.xml"));

    }
    public TestDataService(String testName) throws Exception {
        super(testName);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
                "data");
    }
    public void test() throws Exception {

        DataService dataService = new DataService();
        Data data = new Data();
        data.setIdObject((long) 1);
        data.setDateModified("2018-09-19T13:12:21.136263+03:00");
        data.setDatePublished("2018-09-19T13:12:21.136232+03:00");
        data.setDocumentOf("tender");
        data.setDocumentType("subContract");
        data.setFormat("text/plain");
        data.setHash("md5:232dba893a22ac722249ad92f8bccf22");
        data.setId("4f6d6dc59d1844bb80143ccc2e727a2f");
        data.setTitle("11.09.2018.xlsx");
        data.setUrl("https://public-docs-sandbox.prozorro.gov.ua/get/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D");
        dataService.save(data);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("data");

        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getResourceAsStream("/dataSalaryTestDataSet_Expected.xml"));

        ITable expectedTable = expectedDataSet.getTable("data");
        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);


    }

}
