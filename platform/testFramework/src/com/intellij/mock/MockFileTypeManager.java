// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.mock;

import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class MockFileTypeManager extends FileTypeManagerEx {
  private final FileType fileType;

  public MockFileTypeManager(FileType fileType) {
    this.fileType = fileType;
  }

  @Override
  public void registerFileType(@NotNull FileType fileType) {
  }

  @Override
  public void unregisterFileType(@NotNull FileType fileType) {
  }

  @Override
  @NotNull
  public String getIgnoredFilesList() {
    throw new IncorrectOperationException();
  }

  @Override
  public void setIgnoredFilesList(@NotNull String list) {
  }

  @Override
  public boolean isIgnoredFilesListEqualToCurrent(@NotNull String list) {
    return false;
  }

  public void save() {
  }

  @Override
  @NotNull
  public String getExtension(@NotNull String fileName) {
    return "";
  }

  @Override
  public void registerFileType(@NotNull FileType type, @NotNull List<? extends FileNameMatcher> defaultAssociations) {
  }

  @Override
  public void fireFileTypesChanged() {
  }

  @Override
  @NotNull
  public FileType getFileTypeByFileName(@NotNull String fileName) {
    return fileType;
  }

  @Override
  @NotNull
  public FileType getFileTypeByFile(@NotNull VirtualFile file) {
    return fileType;
  }

  @Override
  @NotNull
  public FileType getFileTypeByExtension(@NotNull String extension) {
    return fileType;
  }

  @Override
  @NotNull
  public FileType[] getRegisteredFileTypes() {
    return FileType.EMPTY_ARRAY;
  }

  @Override
  public boolean isFileIgnored(@NotNull String name) {
    return false;
  }

  @Override
  public boolean isFileIgnored(@NotNull VirtualFile file) {
    return false;
  }

  @Override
  @NotNull
  public String[] getAssociatedExtensions(@NotNull FileType type) {
    return ArrayUtilRt.EMPTY_STRING_ARRAY;
  }

  @Override
  public void fireBeforeFileTypesChanged() {
  }

  @Override
  public void addFileTypeListener(@NotNull FileTypeListener listener) {
  }

  @Override
  public void removeFileTypeListener(@NotNull FileTypeListener listener) {
  }

  @Override
  public FileType getKnownFileTypeOrAssociate(@NotNull VirtualFile file, @NotNull Project project) {
    return file.getFileType();
  }

  @Override
  @NotNull
  public List<FileNameMatcher> getAssociations(@NotNull FileType type) {
    return Collections.emptyList();
  }

  @Override
  public void associate(@NotNull FileType type, @NotNull FileNameMatcher matcher) {
  }

  @Override
  public void removeAssociation(@NotNull FileType type, @NotNull FileNameMatcher matcher) {
  }

  @Override
  @NotNull
  public FileType getStdFileType(@NotNull @NonNls final String fileTypeName) {
    if ("ARCHIVE".equals(fileTypeName)) return UnknownFileType.INSTANCE;
    if ("PLAIN_TEXT".equals(fileTypeName)) return PlainTextFileType.INSTANCE;
    if ("CLASS".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.JavaClassFileType", fileTypeName);
    if ("JAVA".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.JavaFileType", fileTypeName);
    if ("XML".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.XmlFileType", fileTypeName);
    if ("DTD".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.DTDFileType", fileTypeName);
    if ("JSP".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.NewJspFileType", fileTypeName);
    if ("JSPX".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.JspxFileType", fileTypeName);
    if ("HTML".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.HtmlFileType", fileTypeName);
    if ("XHTML".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.ide.highlighter.XHtmlFileType", fileTypeName);
    if ("JavaScript".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.lang.javascript.JavaScriptFileType", fileTypeName);
    if ("Properties".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.lang.properties.PropertiesFileType", fileTypeName);
    if ("GUI_DESIGNER_FORM".equals(fileTypeName)) return loadFileTypeSafe("com.intellij.uiDesigner.GuiFormFileType", fileTypeName);
    return new MockLanguageFileType(PlainTextLanguage.INSTANCE, StringUtil.toLowerCase(fileTypeName));
  }

  private static FileType loadFileTypeSafe(final String className, String fileTypeName) {
    try {
      return (FileType)Class.forName(className).getField("INSTANCE").get(null);
    }
    catch (Exception ignored) {
      return new MockLanguageFileType(PlainTextLanguage.INSTANCE, StringUtil.toLowerCase(fileTypeName));
    }
  }

  @Override
  public boolean isFileOfType(@NotNull VirtualFile file, @NotNull FileType type) {
   return false;
  }

  @Nullable
  @Override
  public FileType findFileTypeByName(@NotNull String fileTypeName) {
    return null;
  }
}
