/**
 * generated by Xtext 2.25.0
 */
package io.github.forsyde.io.meta;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Trait Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.forsyde.io.meta.VertexTraitProperty#getName <em>Name</em>}</li>
 *   <li>{@link io.github.forsyde.io.meta.VertexTraitProperty#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see io.github.forsyde.io.meta.MetaPackage#getVertexTraitProperty()
 * @model
 * @generated
 */
public interface VertexTraitProperty extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see io.github.forsyde.io.meta.MetaPackage#getVertexTraitProperty_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link io.github.forsyde.io.meta.VertexTraitProperty#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(VertexTraitPropertyType)
   * @see io.github.forsyde.io.meta.MetaPackage#getVertexTraitProperty_Type()
   * @model containment="true"
   * @generated
   */
  VertexTraitPropertyType getType();

  /**
   * Sets the value of the '{@link io.github.forsyde.io.meta.VertexTraitProperty#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(VertexTraitPropertyType value);

} // VertexTraitProperty
