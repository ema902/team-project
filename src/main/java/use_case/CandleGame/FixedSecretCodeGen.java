package use_case.CandleGame;


import use_case.SecretCodeGenerator.SecretCodeGenerator;

public class FixedSecretCodeGen implements SecretCodeGenerator {
    private final String secretCode;

    public FixedSecretCodeGen(String secretCode){
        this.secretCode = secretCode;
    }

    @Override
    public String generate() {
        return secretCode;
    }
}
