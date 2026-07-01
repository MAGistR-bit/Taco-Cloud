package sia.tacocloud.web.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.data.jpa.IngredientRepository;
import sia.tacocloud.data.jpa.TacoRepository;
import sia.tacocloud.data.jpa.UserRepository;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.domain.User;

import java.util.Arrays;

@Profile(value = "!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder,
                                        TacoRepository tacoRepository) { // user repo for ease of testing with a built-in user
        return args -> {
            repo.deleteAll();
            userRepo.deleteAll();

            final Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            final Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            final Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            final Ingredient carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            final Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            final Ingredient lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
            final Ingredient cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
            final Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            final Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            final Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

            repo.save(flourTortilla);
            repo.save(cornTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(tomatoes);
            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(salsa);
            repo.save(sourCream);

            userRepo.save(new User("jordon", encoder.encode("password"),
                    "Craig Walls", "123 North Street", "Cross Roads", "TX",
                    "76227", "123-123-1234"));

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);
        };
    }
}
