package cartapp.BasePackage;

import org.testng.annotations.DataProvider;

public class TestUtillities extends BaseTest {

    @DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][] {
                {1, "index.html"},
        };
    }
}
