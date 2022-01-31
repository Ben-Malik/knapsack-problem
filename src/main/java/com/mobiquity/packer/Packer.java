package com.mobiquity.packer;

import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.FileReaderService;
import com.mobiquity.service.LineParserService;
import com.mobiquity.service.PackageProblemService;

/**
 * API class of the project.
 * 
 * @author ben-malik
 */
public class Packer {

  // Default constructor.
  private Packer() {
  }

  /***
   * Parses and packs the items in a given file's path, and returns a string of
   * most optimum solution to the problem.
   * 
   * @param filePath The string value of the file path to be parsed.
   * @return a String of the optimum solution.
   * @throws APIException in case of a failure occurrence.
   */
  public static String pack(String filePath) throws APIException {
    FileReaderService reader = new FileReaderService();
    LineParserService lineParserService = new LineParserService();
    PackageProblemService problemService = new PackageProblemService();

    try {
      return reader.readLines(filePath)
          .map(lineParserService::parse)
          .map(problemService::solve)
          .map(List<Integer>::toString)
          .collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new APIException(e.getMessage(), e);
    }
  }
}
