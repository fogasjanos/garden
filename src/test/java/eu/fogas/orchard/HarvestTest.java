package eu.fogas.orchard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HarvestTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/harvestTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void harvest(@ConvertWith(OrchardConverter.class) int[][] orchard, int max, int maxWithTokens) {
        Harvest h = new Harvest();

        int result = h.harvest(orchard);

        assertEquals(max, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/harvestTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void harvestWithTokens(@ConvertWith(OrchardConverter.class) int[][] orchard, int max, int maxWithTokens) {
        Harvest h = new Harvest();

        int result = h.harvestWithTokens(orchard);

        assertEquals(maxWithTokens, result);
    }

    static class OrchardConverter implements ArgumentConverter {

        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException("The argument should be a string: " + source);
            }
            try {
                return new ObjectMapper().readValue((String) source, int[][].class);
            } catch (JsonProcessingException e) {
                throw new ArgumentConversionException(e.getMessage());
            }
        }
    }
}
