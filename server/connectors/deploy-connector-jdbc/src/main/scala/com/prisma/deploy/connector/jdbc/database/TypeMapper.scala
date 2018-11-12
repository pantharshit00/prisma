package com.prisma.deploy.connector.jdbc.database

import com.prisma.gc_values.GCValue
import com.prisma.shared.models.Field
import com.prisma.shared.models.TypeIdentifier.TypeIdentifier
import org.jooq.DSLContext
import org.jooq.impl.DSL.name

trait TypeMapper {
  def rawSQLForField(field: Field)(implicit dsl: DSLContext): String = {
    rawSQLFromParts(
      field.dbName,
      field.isRequired,
      field.isList,
      field.typeIdentifier,
      field.isAutoGenerated,
      field.defaultValue
    )
  }

  def rawSQLFromParts(
      name: String,
      isRequired: Boolean,
      isList: Boolean,
      typeIdentifier: TypeIdentifier,
      isAutoGenerated: Boolean = false,
      defaultValue: Option[GCValue] = None
  )(implicit dsl: DSLContext): String

  def rawSqlTypeForScalarTypeIdentifier(isList: Boolean, t: TypeIdentifier): String

  def esc(n: String)(implicit dsl: DSLContext): String = dsl.render(name(n))
}