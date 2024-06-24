package personal.projects.TopShop;

import com.stripe.Stripe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import personal.projects.TopShop.controller.auth.AuthController;
import personal.projects.TopShop.dao.ClientRepository;
import personal.projects.TopShop.dao.ProductRepository;
import personal.projects.TopShop.dao.SaleRepository;
import personal.projects.TopShop.dao.UserImageRepository;
import personal.projects.TopShop.domaine.client.CreateClientRequest;
import personal.projects.TopShop.domaine.product.CreateProductRequest;
import personal.projects.TopShop.domaine.user.PermissionVo;
import personal.projects.TopShop.domaine.user.RoleVo;
import personal.projects.TopShop.enums.Categories;
import personal.projects.TopShop.enums.Permissions;
import personal.projects.TopShop.enums.Roles;
import personal.projects.TopShop.service.*;
import personal.projects.TopShop.service.models.Client;
import personal.projects.TopShop.service.models.Product;
import personal.projects.TopShop.service.models.Sale;
import personal.projects.TopShop.service.models.UserImage;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TopShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopShopApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner init(
			IUserService userService,
			IProductService productService,
			ProductRepository productRepository,
			IClientService clientService,
			ICategoryService categoryService,
			AuthController authController,
			SaleServiceImpl saleService,
			SaleRepository saleRepository,
			UserImageRepository userImageRepository,
			ClientRepository clientRepository){

		return (a) -> {

			Stripe.apiKey = "sk_test_51PLNwT076td8p3Y8yhiTcsgoAXz2Oegy9QK7grksTuywOuLNlU2WsW4wyA7wuXiz7vXuaMCepaMMw5RANqwnUyl600jV7TFae8";


			Client client = clientRepository.findByUsername("unxeen").get();
			UserImage userImage = userImageRepository.findById(8L).get();
			userImage.setUsername(client.getUsername());
			userImageRepository.save(userImage);


//			//Create and persist all permissions
//			Arrays.stream(Permissions.values()).toList().forEach((permission) -> {
//				userService.savePermission(PermissionVo.builder().authority(permission.name()).build());
//			});
//
//
//			//Create and save roles here
//			RoleVo roleClient = RoleVo.builder()
//					.authority(Roles.ROLE_CLIENT.name())
//					.authorities(List.of(
//							userService.getPermissionByAuthority(Permissions.GET_CLIENT_BY_IDENTITY.name()),
//							userService.getPermissionByAuthority(Permissions.UPDATE_CLIENT.name())
//					))
//					.build();
//
//			userService.saveRole(roleClient);
//
//
//			//Save all Categories in the enum
//			Arrays.stream(Categories.values()).forEach(
//					category -> categoryService.saveCategory(category.name())
//			);
//
//
//
//
//
//
//
//			// Initialize data here
//			CreateClientRequest request = CreateClientRequest.builder()
//					.username("unxeen")
//					.password("Passport24!")
//					.firstname("Ayoub")
//					.lastname("Elghazi")
//					.email("ayoubelghazi6@gmail.com")
//					.postalAddress("123STREET")
//					.build();
//
//			clientService.createClient(request);
//
//
//
//			CreateProductRequest product1 = CreateProductRequest.builder()
//					.label("Laptop")
//					.description("Powerful laptop for work and gaming")
//					.price(1200.0)
//					.stock(10)
//					.username("unxeen")
//					.cat("TECHNOLOGY")
//					.build();
//			CreateProductRequest product2 = CreateProductRequest.builder()
//					.label("Sofa")
//					.description("Comfortable and stylish sofa for your living room")
//					.price(500.0)
//					.stock(3)
//					.username("unxeen")
//					.cat("FURNITURE")
//					.build();
//			CreateProductRequest product3 = CreateProductRequest.builder()
//					.label("T-shirt")
//					.description("Casual t-shirt for everyday wear")
//					.price(15.0)
//					.stock(20)
//					.username("unxeen")
//					.cat("FASHION")
//					.build();
//			CreateProductRequest product4 = CreateProductRequest.builder()
//					.label("Cookware Set")
//					.description("Complete set of pots and pans for your kitchen")
//					.price(100.0)
//					.stock(8)
//					.username("unxeen")
//					.cat("KITCHEN")
//					.build();
//			CreateProductRequest product5 = CreateProductRequest.builder()
//					.label("Notebooks")
//					.description("High-quality notebooks for writing and sketching")
//					.price(8.0)
//					.stock(30)
//					.username("unxeen")
//					.cat("SCHOOL")
//					.build();
//			CreateProductRequest product6 = CreateProductRequest.builder()
//					.label("Desktop Computer")
//					.description("High-performance desktop computer for professionals")
//					.price(1500.0)
//					.stock(5)
//					.username("unxeen")
//					.cat("TECHNOLOGY")
//					.build();
//			CreateProductRequest product7 = CreateProductRequest.builder()
//					.label("Coffee Table")
//					.description("Modern coffee table with storage compartments")
//					.price(300.0)
//					.stock(7)
//					.username("unxeen")
//					.cat("FURNITURE")
//					.build();
//			CreateProductRequest product8 = CreateProductRequest.builder()
//					.label("Dress")
//					.description("Elegant dress for special occasions")
//					.price(50.0)
//					.stock(15)
//					.username("unxeen")
//					.cat("FASHION")
//					.build();
//			CreateProductRequest product9 = CreateProductRequest.builder()
//					.label("Blender")
//					.description("Powerful blender for smoothies and shakes")
//					.price(80.0)
//					.stock(10)
//					.username("unxeen")
//					.cat("KITCHEN")
//					.build();
//			CreateProductRequest product10 = CreateProductRequest.builder()
//					.label("Backpack")
//					.description("Durable backpack with multiple compartments")
//					.price(25.0)
//					.stock(25)
//					.username("unxeen")
//					.cat("SCHOOL")
//					.build();
//
//			productService.save(product1);
//			productService.save(product2);
//			productService.save(product3);
//			productService.save(product4);
//			productService.save(product5);
//			productService.save(product6);
//			productService.save(product7);
//			productService.save(product8);
//			productService.save(product9);
//			productService.save(product10);

		};


	}

}
