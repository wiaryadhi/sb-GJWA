package com.bcafinance.gjwaspringbootjpa.services;


import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Movies;
import com.bcafinance.gjwaspringbootjpa.repos.MovieRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movies> findAllMovies() {

        return movieRepo.findAll();
    }

    public Movies findByIdMovies(Long id) throws Exception {
        return movieRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Movies findByTitleMovies(String title) throws Exception {

        return movieRepo.findByTitle(title).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
    }

    public List<Movies> findByTitleMovieLike(String title) throws Exception {

        return movieRepo.findByTitleLike(title);
    }

    public List<Movies> findByTitleStartsWith(String title) throws Exception{
        return movieRepo.findByTitleStartsWith(title);
    }
    public List<Movies> findTitleEndsWith(String title)
    {
        return movieRepo.findByTitleEndsWith(title);
    }

    public List<Movies> findTitleNotLike(String title)
    {
        return movieRepo.findByTitleNotLike(title);
    }


    public void saveMovies(Movies movies) throws Exception {
        if (movies.getTitle() == null) throw new DataIntegrityViolationException(ConstantMessage.ERROR_MOVIE_INVALID);
        if (movies.getReleaseDate() == null)
            throw new DataIntegrityViolationException(ConstantMessage.ERROR_MOVIE_INVALID);

        // error dibawah karena data integrity exception inputnya adalah String, sedangkan budget adalah long
//        if(movies.getBudget()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(movies.getRevenue()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(movies.getRating()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        // tidak melakukan validasi karena
//        FormatValidation.phoneNumberFormatValidation(customers.getPhoneNumber());
//        FormatValidation.emailFormatValidation(customers.getEmail());
//        FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());

        Optional<Movies> moviesByTitle = movieRepo.findByTitle(movies.getTitle());
//        if (moviesByTitle.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
//        }
        movieRepo.save(movies);
    }


    @Transactional
    public void updateMovieById(Movies m) throws Exception {

        Movies movies = movieRepo.findById(m.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));

        movies.setModifiedBy("1");
        movies.setModifiedDate(new Date());
        if (m.getTitle() != null
                && !Objects.equals(movies.getTitle(), m.getTitle()) //Membandingkan string dari api dan dari database
                && !m.getTitle().equals("")) {
            movies.setTitle(m.getTitle());//BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getBudget() != 0
                && m.getBudget() == movies.getBudget()) {
            movies.setBudget(m.getBudget()); //BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getReleaseDate().toString() != null &&
                !m.getReleaseDate().toString().equals("") &&
                !Objects.equals(movies.getReleaseDate().toString(), m.getReleaseDate().toString())) {

            // FormatValidation.emailFormatValidation(m.getTitle());

            Optional<Movies> mBeanOptional = movieRepo.findByTitle(m.getTitle());
//            if (mBeanOptional.isPresent())//it means if exists
//            {
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
//            }
            movies.setTitle(m.getTitle());
        }

        if (m.getRevenue() != 0
                && m.getRevenue() == movies.getRevenue()) {
            movies.setRevenue(m.getRevenue()); //BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getRating() != 0 &&
                m.getRevenue() == movies.getRevenue()) {
//            FormatValidation.phoneNumberFormatValidation(m.1getPhoneNumber());
            movies.setRating(m.getRating());
        }
        if (m.getRuntime() != 0 &&
                m.getRuntime() < 1 //validasi runtime movie harus lebih dari 1 menit
                && m.getRuntime() == movies.getRuntime()) {
            movies.setRuntime(m.getRuntime());
        }
    }
}
//
//    @Transactional
//    public void updateCustomerByIdV2(Customers c) throws Exception{
//
//        Customers customers = customerRepo.findById(c.getId()).orElseThrow(()->
//                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
//
//        customers.setModifiedBy("1");
//        customers.setModifiedDate(new Date());
//        if(c.getFirstName() != null
//                && !Objects.equals(customers.getFirstName(),c.getFirstName())
//                && !c.getFirstName().equals(""))
//        {
//            customers.setFirstName(c.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getMiddleName() != null
//                && !Objects.equals(customers.getMiddleName(),c.getMiddleName())
//                && !c.getMiddleName().equals(""))
//        {
//            customers.setMiddleName(c.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getEmail() != null &&
//                c.getEmail().length()>0 &&
//                !Objects.equals(customers.getEmail(),c.getEmail()))
//
//        {
//            FormatValidation.emailFormatValidation(c.getEmail());
//
//            Optional<Customers> cBeanOptional = customerRepo.findByEmail(c.getEmail());
//            if(cBeanOptional.isPresent())//it means if exists
//            {
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
//            }
//            customers.setEmail(c.getEmail());
//        }
//
//        if(c.getAddress() != null
//                && !Objects.equals(customers.getAddress(),c.getAddress())
//                && !c.getAddress().equals(""))
//        {
//            customers.setAddress(c.getAddress());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getPhoneNumber() != null &&
//                c.getPhoneNumber().length()>0 &&
//                !Objects.equals(customers.getPhoneNumber(),c.getPhoneNumber())){
//
//            FormatValidation.phoneNumberFormatValidation(c.getPhoneNumber());
//            customers.setPhoneNumber(c.getPhoneNumber());
//        }
//        if(c.getBirthDate() != null &&
//                !c.getBirthDate().toString().equals("") &&
//                !Objects.equals(customers.getBirthDate().toString(),c.getBirthDate().toString()))
//        {
//            FormatValidation.dateFormatYYYYMMDDValidation(c.getBirthDate().toString());
//            customers.setBirthDate(c.getBirthDate());
//        }
//    }
//}